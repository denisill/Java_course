package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String hometelefon;
    private final String email;

    public ContactData(String firstname, String lastname, String hometelefon, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.hometelefon = hometelefon;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getHometelefon() {
        return hometelefon;
    }

    public String getEmail() {
        return email;
    }
}
