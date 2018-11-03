package de.sharknoon.slash.Login;

public class LoginResponse {

    private String status;
    private String message;
    private String sessionid;

    public LoginResponse(String status, String message, String sessionid) {
        this.status = status;
        this.message = message;
        this.sessionid = sessionid;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getSessionid() {
        return sessionid;
    }
}
