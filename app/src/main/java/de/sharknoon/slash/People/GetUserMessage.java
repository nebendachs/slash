package de.sharknoon.slash.People;

@SuppressWarnings({"FieldCanBeLocal", "UnusedParameters"})
public class GetUserMessage {
    private final String sessionid;
    private final String status = "GET_USER";
    private final String userID;

    public GetUserMessage(String sessionid, String userID) {
        this.sessionid = sessionid;
        this.userID = userID;
    }
}
