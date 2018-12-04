package de.sharknoon.slash.ChatMessages;

public class GetChat {
    private String sessionId;
    private final String status = "GET_CHAT";
    private String partnerUserId;

        public GetChat(String sessionId, String partnerUserId){
            this.sessionId = sessionId;
            this.partnerUserId = partnerUserId;
        }

    public String getPartnerUserId() {
        return partnerUserId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getStatus() {
        return status;
    }
}
