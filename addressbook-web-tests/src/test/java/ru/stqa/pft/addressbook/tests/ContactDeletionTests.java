package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoContactList();
            if (! app.getContactHelper().isThereAContact()){
                app.getContactHelper().initContactCreation();
                app.getContactHelper().createContact(new ContactData("name1", null, null, null ));
                app.getNavigationHelper().returnToHomePage();
            }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().closeAlert();
    }
}
