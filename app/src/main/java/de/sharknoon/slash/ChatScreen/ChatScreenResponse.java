package de.sharknoon.slash.ChatScreen;

public class ChatScreenResponse {
    private String[] chatMessages;
    private String status;
    private String message;
    private String sessionid;

    public ChatScreenResponse(String status, String message, String sessionid, String[] messages) {
        this.chatMessages = messages;
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

    public String[] getChatMessages() { return chatMessages; }
}
