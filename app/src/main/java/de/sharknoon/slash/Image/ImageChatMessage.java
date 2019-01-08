package de.sharknoon.slash.Image;

public class ImageChatMessage {

    String status = "ADD_CHAT_MESSAGE";
    String sessionid;
    String chatID;
    String messageType;

    public ImageChatMessage(String sessionid, String chatID, String messageType) {
        this.sessionid = sessionid;
        this.chatID = chatID;
        this.messageType = messageType;
    }

    public String getStatus() {
        return status;
    }

    public String getSessionid() {
        return sessionid;
    }

    public String getChatID() {
        return chatID;
    }

    public String getMessageType() {
        return messageType;
    }
}
