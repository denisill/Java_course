package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {

        app.goTo().contactPage();
        Contacts before = app.contact().all();
        app.goTo().contactAdd();
        ContactData contact = new ContactData().withFirstname("Denis").withLastname("Ill");
        app.contact().create(contact);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        app.getSessionHelper().logout();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }


}
