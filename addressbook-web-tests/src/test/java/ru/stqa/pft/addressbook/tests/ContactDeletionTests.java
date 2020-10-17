package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoContactList();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().initContactCreation();
            app.getContactHelper().createContact(new ContactData("name1", null, null, null));
            app.getNavigationHelper().returnToHomePage();
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(before - 1);
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().closeAlert();
        int after = app.getContactHelper().getContactCount();

        Assert.assertEquals(after, before - 1);
    }
}
