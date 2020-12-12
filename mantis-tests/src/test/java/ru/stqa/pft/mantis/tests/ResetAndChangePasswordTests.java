package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetAndChangePasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testResetAndChangePassword() throws IOException {
        Users users = app.db().usersWithoutAdmin();

        UserData user = users.iterator().next();
        String userName = user.getName();
        String userEmail = user.getEmail();
        String newPassword = "qwerty";
        app.session().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.goTo().managePage();
        app.goTo().usersManageTab();
        app.user().selectById(user.getId());
        app.user().initPasswordReset();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 60000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, userEmail);
        app.registration().finish(confirmationLink, newPassword);
        assertTrue(app.newSession().login(userName, newPassword));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}
