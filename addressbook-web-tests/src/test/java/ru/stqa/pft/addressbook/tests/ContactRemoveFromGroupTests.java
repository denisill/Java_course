package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.not;

public class ContactRemoveFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsContact() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("name2").withLastname("lastname").withHomePhone("8(495)0010000").withMobilePhone("89654564").withWorkPhone("67464546").
                    withEmail("denisill@mail.ru").withEmail2("123@mail.ru").withEmail3("rgre@mail.ru"));
            app.goTo().contactPage();
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("footer"));
            app.goTo().contactPage();
        }

        if (app.db().groups().stream().filter(g -> !g.getContacts().isEmpty()).collect(toSet()).isEmpty()) {
            GroupData groupTemp = app.db().groups().iterator().next();
            ContactData contactTemp = app.db().contacts().iterator().next();
            app.contact().addToGroup(contactTemp, groupTemp);
        }
    }

    @Test
    public void testContactRemoveFromGroup() {
        app.goTo().contactPage();
        GroupData group = app.db().groups().
                stream().filter(g -> !g.getContacts().isEmpty()).collect(toSet()).
                iterator().next();
        ContactData contact = app.db().contacts().
                stream().filter(c -> c.getGroups().contains(group)).collect(toSet()).
                iterator().next();
        app.contact().removedFromGroup(contact, group);

        int contactId = contact.getId();
        contact = app.db().contacts().
                stream().filter(e -> (e.getId() == contactId)).collect(Collectors.toSet()).
                iterator().next();
        assertThat(contact.getGroups(), not(contains(group)));
    }

}
