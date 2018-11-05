package de.sharknoon.slash.ChatScreen;

public class ChatScreenMessage {

    private String sessionId;
    private String message;

    ChatScreenMessage(String sessionId, String message){
        this.sessionId = sessionId;
        this.message = message;
    }
}
