package de.sharknoon.slash.Login;

public class LoginMessage {

    private String usernameOrEmail;
    private String password;

    public LoginMessage(String usernameOrEmail, String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }
}
