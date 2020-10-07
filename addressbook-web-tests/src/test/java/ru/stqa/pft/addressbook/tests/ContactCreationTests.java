package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().createContact(new ContactData("Denis", "Illarionov", "8(495)0010000", "denisill@mail.ru"));
    app.getNavigationHelper().returnToHomePage();
    app.getSessionHelper().logout();
  }


}
