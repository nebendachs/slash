package de.sharknoon.slash.ChatMessages;

@SuppressWarnings({"FieldCanBeLocal", "UnusedParameters"})
public class SendProjectMessage {
    private final String sessionid;
    private final String status = "ADD_PROJECT_MESSAGE";
    private final String projectID;
    private final String messageType;
    private final String messageContent;
    private final String messageSubject;
    private final String messageEmotion;

    public SendProjectMessage(String sessionId, String projectID, String messageType, String messageContent, String messageSubject, String messageEmotion){
        this.projectID = projectID;
        this.sessionid = sessionId;
        this.messageType = messageType;
        this.messageContent = messageContent;
        this.messageSubject = messageSubject;
        this.messageEmotion = messageEmotion;
    }
}
