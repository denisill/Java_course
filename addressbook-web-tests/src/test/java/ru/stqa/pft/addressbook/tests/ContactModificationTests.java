package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsContact() {
        app.goTo().contactPage();
        if (app.contact().list().size() == 0) {
            app.contact().initContactCreation();
            app.contact().create(new ContactData("name1", null, null, null));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactModification() {

        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Denis", "Illarionov", "8(495)0010000", "denisill@mail.ru");
        int index = before.size() - 1;
        app.contact().modify(contact, index);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();

        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
