package de.sharknoon.slash.ChatMessages;

public class GetChat {
    private String sessionid;
    private final String status = "GET_CHAT";
    private String partnerUserID;

        public GetChat(String sessionid, String partnerUserID){
            this.sessionid = sessionid;
            this.partnerUserID = partnerUserID;
        }

    public String getPartnerUserId() {
        return partnerUserID;
    }

    public String getSessionId() {
        return sessionid;
    }

    public String getStatus() {
        return status;
    }
}
