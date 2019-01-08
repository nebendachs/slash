package de.sharknoon.slash.Image;

public class ImageChatMessage {

    final String status = "ADD_CHAT_MESSAGE";
    String sessionid;
    String chatID;
    String messageType = "IMAGE";

    public ImageChatMessage(String sessionid, String chatID) {
        this.sessionid = sessionid;
        this.chatID = chatID;
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
