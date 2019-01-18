package de.sharknoon.slash.Registration;

@SuppressWarnings({"FieldCanBeLocal", "UnusedParameters"})
class RegistrationMessage {
    private final String username;
    private final String email;
    private final String password;

    public RegistrationMessage(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
