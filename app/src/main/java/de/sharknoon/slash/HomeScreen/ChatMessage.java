package de.sharknoon.slash.HomeScreen;

public class ChatMessage {

    private String sessionId;
    private final String status = "ADD_MESSAGE";
    private String message;
    private String chatID;

    public ChatMessage(String sessionId, String chatID, String message){
        this.message = message;
        this.chatID = chatID;
        this.sessionId = sessionId;
    }

    public String getChatID(){ return chatID; }

    public String getMessage(){ return message;}

    public String getStatus(){ return status;}

    public String getSessionID(){ return sessionId; }
}
