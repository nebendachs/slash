package de.sharknoon.slash.HomeScreen;

public class ChatMessage {

    private String sessionid;
    private final String status = "ADD_MESSAGE";
    private String chatID;
    private String type;
    private String message;
    private String header;

    public ChatMessage(String sessionId, String chatID, String type, String message, String header){
        this.chatID = chatID;
        this.sessionid = sessionId;
        this.type = type;
        this.message = message;
        this.header = header;
    }

    public String getChatID(){ return chatID; }

    public String getStatus(){ return status;}

    public String getSessionID(){ return sessionid; }

    public String getType(){ return type; }

    public String getMessage(){ return  message; }

    public String getHeader(){ return  header; }
}
