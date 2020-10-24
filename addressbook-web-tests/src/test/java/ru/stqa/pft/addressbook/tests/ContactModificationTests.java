package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsContact() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().initContactCreation();
            app.contact().create(new ContactData().withFirstname("Den").withLastname("lastname"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactModification() {

        Contacts before = app.contact().all();
        ContactData modifyContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifyContact.getId()).withFirstname("Denis").withLastname("Illarionov").withHometelefon("8(495)0010000").withEmail("denisill@mail.ru");
        app.contact().modify(contact);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.withOut(modifyContact).withAdded(contact)));
    }


}
