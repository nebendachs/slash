package de.sharknoon.slash.ChatMessages;

@SuppressWarnings({"FieldCanBeLocal", "UnusedParameters"})
class ImageLoaderMessage {
    private final String sessionId;
    private final String status = "DOWNLOAD";

    public ImageLoaderMessage(String sessionId){
        this.sessionId = sessionId;
    }
}
