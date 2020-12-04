package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class ContactAddToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsContact() {
        if (app.db().contacts().stream().filter(e -> (e.getGroups().isEmpty())).collect(Collectors.toSet()).size() == 0) {
            app.contact().create(new ContactData().withFirstname("name2").withLastname("lastname").withHomePhone("8(495)0010000").withMobilePhone("89654564").withWorkPhone("67464546").
                    withEmail("denisill@mail.ru").withEmail2("123@mail.ru").withEmail3("rgre@mail.ru"));
            app.goTo().contactPage();
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("footer"));
            app.goTo().contactPage();
        }

    }

    @Test
    public void testContactAddToGroup() {
        app.goTo().contactPage();
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        ContactData contact = app.db().contacts().stream().filter(e -> (e.getGroups().isEmpty())).collect(Collectors.toSet()).
                iterator().next();
        app.contact().addToGroup(contact, group);
        int contactId = contact.getId();
        contact = app.db().contacts().
                stream().filter(e -> (e.getId() == contactId)).collect(Collectors.toSet()).
                iterator().next();

        assertThat(contact.getGroups(), contains(group));
    }

}

/*    @BeforeMethod
    public void ensurePreconditionsContact() {
        app.goTo().contactPage();
        if (app.db().contacts().size() == 0) {
            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                app.group().initGroupCreation();
                app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("footer"));
                app.goTo().contactPage();
            }
            app.contact().initContactCreation();
            app.contact().create(new ContactData().withFirstname("name2").withLastname("lastname").withHomePhone("8(495)0010000").withMobilePhone("89654564").withWorkPhone("67464546").
                    withEmail("denisill@mail.ru").withEmail2("123@mail.ru").withEmail3("rgre@mail.ru"));
            app.goTo().homePage();
        }
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() == groups.size()) {
                app.contact().initContactCreation();
                app.contact().create(new ContactData().withFirstname("name2").withLastname("lastname").
                        withHomePhone("8(495)0010000").withMobilePhone("89654564").withWorkPhone("67464546").
                        withEmail("denisill@mail.ru").withEmail2("123@mail.ru").withEmail3("rgre@mail.ru"));
            }
            return;
        }
        app.goTo().homePage();
    }

    @Test
    public void testContactAddToGroup() {

        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() != groups.size()) {
                Groups groupsBefore = contact.getGroups();
                app.contact().addToGroup(contact);
                Contacts updateContacts = app.db().contacts();
                for (ContactData updateContact : updateContacts) {
                    if (updateContact.getId() == contact.getId()) {
                        Groups groupsAfter = updateContact.getGroups();
                        assertThat(groupsAfter.size(), equalTo(groupsBefore.size() + 1));
                        Groups groupsWithAdded = new Groups(groupsAfter);
                        groupsWithAdded.removeAll(groupsBefore);
                        assertThat(groupsAfter, equalTo(groupsBefore.withAdded(groupsWithAdded.iterator().next())));
                    }
                }
                app.goTo().contactPage();
                app.contact().listAllGroups();
            }
        }
    }

}
*/