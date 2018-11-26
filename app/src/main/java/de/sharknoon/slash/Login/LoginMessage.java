package de.sharknoon.slash.Login;

public class LoginMessage {

    private String usernameOrEmail;
    private String password;
    private String deviceID;

    public LoginMessage(String usernameOrEmail, String password, String deviceID) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
        this.deviceID = deviceID;
    }
}
