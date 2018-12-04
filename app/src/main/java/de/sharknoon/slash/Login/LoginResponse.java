package de.sharknoon.slash.Login;

public class LoginResponse {

    private String status;
    private String message;
    private String sessionid;
    private String userID;

    public LoginResponse(String status, String message, String sessionid, String userID) {
        this.status = status;
        this.message = message;
        this.sessionid = sessionid;
        this.userID = userID;
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

    public String getUserID() {
        return userID;
    }
}
