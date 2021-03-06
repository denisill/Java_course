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
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Den").withLastname("lastname"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactModification() {

        Contacts before = app.db().contacts();
        ContactData modifyContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifyContact.getId()).
                withFirstname("Denis").withLastname("Illarionov").withHomePhone("8(495)0010000").withMobilePhone("89654564").withWorkPhone("67464546").
                withEmail("denisill@mail.ru").withEmail2("123@mail.ru").withEmail3("rgre@mail.ru");
        app.contact().modify(contact);
        app.goTo().homePage();
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.withOut(modifyContact).withAdded(contact)));
    }


}
