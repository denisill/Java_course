package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsContact() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().initContactCreation();
            app.contact().create(new ContactData().withFirstname("Den"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactModification() {

        Set<ContactData> before = app.contact().all();
        ContactData modifyContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifyContact.getId()).withFirstname("Denis").withLastname("Illarionov").withHometelefon("8(495)0010000").withEmail("denisill@mail.ru");
        int index = before.size() - 1;
        app.contact().modify(contact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();

        Assert.assertEquals(after.size(), before.size());

        before.remove(modifyContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }


}
