package de.sharknoon.slash.ChatMessages;

import java.util.Date;

public class ChatMessage {

    private String sessionid;
    private final String status = "ADD_MESSAGE";
    private String chatID;
    private String messageType;
    private String messageContent;
    private String messageSubject;
    private String messageEmotion;

    public ChatMessage(String sessionId, String chatID, String messageType, String messageContent, String messageSubject, String messageEmotion){
        this.chatID = chatID;
        this.sessionid = sessionId;
        this.messageType = messageType;
        this.messageContent = messageContent;
        this.messageSubject = messageSubject;
        this.messageEmotion = messageEmotion;
    }

    public String getChatID(){ return chatID; }

    public String getStatus(){ return status;}

    public String getSessionID(){ return sessionid; }

    public String getMessageType(){ return  messageType; }

    public String getMessageContent() { return messageContent; }

    public String getMessageSubject() { return messageSubject; }

    public String getMessageEmotion() { return messageEmotion; }

}
