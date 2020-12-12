package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {

    public SessionHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        type(By.name("username"), username);
        wd.findElement(By.id("login-form")).submit();
        type(By.name("password"), password);
        wd.findElement(By.id("login-form")).submit();
    }
}
