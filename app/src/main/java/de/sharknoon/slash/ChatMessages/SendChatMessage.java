package de.sharknoon.slash.ChatMessages;

@SuppressWarnings({"FieldCanBeLocal", "UnusedParameters"})
public class SendChatMessage {
    private final String sessionid;
    private final String status = "ADD_CHAT_MESSAGE";
    private final String chatID;
    private final String messageType;
    private final String messageContent;
    private final String messageSubject;
    private final String messageEmotion;

    public SendChatMessage(String sessionId, String chatID, String messageType, String messageContent, String messageSubject, String messageEmotion){
        this.chatID = chatID;
        this.sessionid = sessionId;
        this.messageType = messageType;
        this.messageContent = messageContent;
        this.messageSubject = messageSubject;
        this.messageEmotion = messageEmotion;
    }
}
