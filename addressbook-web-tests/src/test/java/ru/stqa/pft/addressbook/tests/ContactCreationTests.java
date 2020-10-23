package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {

        app.goTo().contactPage();
        Set<ContactData> before = app.contact().all();
        app.goTo().contactAdd();
        ContactData contact = new ContactData().withFirstname("Denis").withLastname("Ill");
        app.contact().create(contact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        app.getSessionHelper().logout();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);

    }


}
