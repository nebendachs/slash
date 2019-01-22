package de.sharknoon.slash.Login;

@SuppressWarnings({"FieldCanBeLocal", "UnusedParameters"})
class LoginMessage {
    private final String usernameOrEmail;
    private final String password;
    private final String deviceID;

    public LoginMessage(String usernameOrEmail, String password, String deviceID) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
        this.deviceID = deviceID;
    }
}
