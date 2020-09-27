package ru.stqa.pft.addressbook;

import org.testng.annotations.*;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    fillGroupForm(new GroupData("test11", "test2", "test3"));
    submitGroupCreation();
    returnToGroupPage();
    logout();
  }


}
