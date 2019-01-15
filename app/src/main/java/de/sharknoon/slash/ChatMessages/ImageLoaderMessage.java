package de.sharknoon.slash.ChatMessages;

public class ImageLoaderMessage {

    private String sessionId;
    private final String status = "DOWNLOAD";

    public ImageLoaderMessage(String sessionId){
        this.sessionId = sessionId;
    }
}
