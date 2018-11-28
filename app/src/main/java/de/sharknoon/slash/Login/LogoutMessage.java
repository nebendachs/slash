package de.sharknoon.slash.Login;

public class LogoutMessage {

    private String sessionid;
    private final String status = "LOGOUT";

    public LogoutMessage(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getSessionid() {
        return sessionid;
    }

    public String getStatus() {
        return status;
    }
}
