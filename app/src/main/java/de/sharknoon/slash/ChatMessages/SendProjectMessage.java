package de.sharknoon.slash.ChatMessages;

public class SendProjectMessage {
    private String sessionid;
    private String status;
    private String projectID;
    private String messageType;
    private String messageContent;
    private String messageSubject;
    private String messageEmotion;

    public SendProjectMessage(String sessionId, String projectID, String messageType, String messageContent, String messageSubject, String messageEmotion, String status){
        this.projectID = projectID;
        this.sessionid = sessionId;
        this.messageType = messageType;
        this.messageContent = messageContent;
        this.messageSubject = messageSubject;
        this.messageEmotion = messageEmotion;
        this.status = status;
    }

    public String getProjectID(){ return projectID; }

    public String getStatus(){ return status;}

    public String getSessionID(){ return sessionid; }

    public String getMessageType(){ return  messageType; }

    public String getMessageContent() { return messageContent; }

    public String getMessageSubject() { return messageSubject; }

    public String getMessageEmotion() { return messageEmotion; }
    }
