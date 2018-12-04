package de.sharknoon.slash.ChatMessages;

public class SendChatMessage {

    private String sessionid;
    private String status;
    private String chatID;
    private String messageType;
    private String messageContent;
    private String messageSubject;
    private String messageEmotion;

    public SendChatMessage(String sessionId, String chatID, String messageType, String messageContent, String messageSubject, String messageEmotion, String status){
        this.chatID = chatID;
        this.sessionid = sessionId;
        this.messageType = messageType;
        this.messageContent = messageContent;
        this.messageSubject = messageSubject;
        this.messageEmotion = messageEmotion;
        this.status = status;
    }

    public String getChatID(){ return chatID; }

    public String getStatus(){ return status;}

    public String getSessionID(){ return sessionid; }

    public String getMessageType(){ return  messageType; }

    public String getMessageContent() { return messageContent; }

    public String getMessageSubject() { return messageSubject; }

    public String getMessageEmotion() { return messageEmotion; }

}
