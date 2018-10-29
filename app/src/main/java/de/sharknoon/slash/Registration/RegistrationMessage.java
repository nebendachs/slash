package de.sharknoon.slash.Registration;

public class RegistrationMessage {

    private String username;
    private String email;
    private String password;

    public RegistrationMessage(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
