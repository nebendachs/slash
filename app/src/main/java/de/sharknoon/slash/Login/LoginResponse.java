package de.sharknoon.slash.Login;

@SuppressWarnings({"FieldCanBeLocal", "UnusedParameters"})
class LoginResponse {
    private final String status;
    private final String message;
    private final String sessionid;
    private final String userID;

    public LoginResponse(String status, String message, String sessionid, String userID) {
        this.status = status;
        this.message = message;
        this.sessionid = sessionid;
        this.userID = userID;
    }

    public String getStatus() {
        return status;
    }

    public String getSessionid() {
        return sessionid;
    }

    public String getUserID() {
        return userID;
    }
}
