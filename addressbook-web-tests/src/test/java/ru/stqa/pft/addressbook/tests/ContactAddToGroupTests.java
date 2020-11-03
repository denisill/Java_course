package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsContact() {
        app.goTo().contactPage();
        if (app.db().contacts().size() == 0) {
            app.contact().initContactCreation();
            app.contact().create(new ContactData().withFirstname("name2").withLastname("lastname").withHomePhone("8(495)0010000").withMobilePhone("89654564").withWorkPhone("67464546").
                    withEmail("denisill@mail.ru").withEmail2("123@mail.ru").withEmail3("rgre@mail.ru"));
            app.goTo().homePage();
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().initGroupCreation();
            app.group().create(new GroupData().withName("test1"));
            app.goTo().contactPage();
        }
    }

    @Test
    public void testContactAddToGroup() {

        Contacts before = app.db().contacts();
        ContactData findContact = before.iterator().next();
        ContactData contactAdd = new ContactData().withId(findContact.getId()).withFirstname("name2").withLastname("lastname").withHomePhone("8(495)0010000").withMobilePhone("89654564").withWorkPhone("67464546").
                withEmail("denisill@mail.ru").withEmail2("123@mail.ru").withEmail3("rgre@mail.ru");
        app.contact().addToGroup(contactAdd);
        app.goTo().contactPage();
        Contacts after =app.db().contacts();
        assertThat(after, equalTo(before.withOut(findContact).withAdded(contactAdd)));

    }



}
