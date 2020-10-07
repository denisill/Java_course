package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoContactList();
            if (!app.getContactHelper().isThereAContact()) {
                app.getContactHelper().initContactCreation();
                app.getContactHelper().createContact(new ContactData("name1", null, null, null));
                app.getNavigationHelper().returnToHomePage();
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Denis", "Illarionov", "8(495)0010000", "denisill@mail.ru"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().returnToHomePage();

    }
}
