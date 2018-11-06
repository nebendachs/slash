package de.sharknoon.slash.HomeScreen;

public class ChatMessage {

    private final String status = "ADD_MESSAGE";
    private String chatID;
    private String sessionId;
    private String message;

    public ChatMessage(String sessionId, String chatID, String message){
        this.sessionId = sessionId;
        this.chatID = chatID;
        this.message = message;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getChatID(){ return chatID; }

    public String getSessionID(){ return sessionId; }

    public String getStatus(){ return status;}

    public String getMessage(){ return message;}
}
