package de.sharknoon.slash.Login;

@SuppressWarnings({"FieldCanBeLocal", "UnusedParameters"})
public class LogoutMessage {
    private final String sessionid;
    private final String status = "LOGOUT";

    public LogoutMessage(String sessionid) {
        this.sessionid = sessionid;
    }
}
