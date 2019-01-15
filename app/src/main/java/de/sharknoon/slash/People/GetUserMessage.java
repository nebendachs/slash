package de.sharknoon.slash.People;

public class GetUserMessage {
    private String sessionid;
    private final String status = "GET_USER";
    private String userID;

    public GetUserMessage(String sessionid, String userID) {
        this.sessionid = sessionid;
        this.userID = userID;
    }

    public String getSessionid() {
        return sessionid;
    }

    public String getStatus() {
        return status;
    }

    public String getUserID() {
        return userID;
    }
}
