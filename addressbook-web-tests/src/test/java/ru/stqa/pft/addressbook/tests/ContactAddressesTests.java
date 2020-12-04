package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressesTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsContact() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("name2").withLastname("lastname"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactAddresses() {

        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllAddress(), equalTo(mergeAddresses(contactInfoFromEditForm)));

    }

    private String mergeAddresses(ContactData contact) {
        return Arrays.asList(contact.getAddress()).
                stream().filter((s) -> !s.equals("")).
                collect(Collectors.joining("\n"));

    }

}
