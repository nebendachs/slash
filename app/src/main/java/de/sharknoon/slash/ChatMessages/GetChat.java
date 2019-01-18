package de.sharknoon.slash.ChatMessages;

@SuppressWarnings({"FieldCanBeLocal", "UnusedParameters"})
public class GetChat {
    private final String sessionid;
    private final String status = "GET_CHAT";
    private final String partnerUserID;

    public GetChat(String sessionid, String partnerUserID){
        this.sessionid = sessionid;
        this.partnerUserID = partnerUserID;
    }
}
