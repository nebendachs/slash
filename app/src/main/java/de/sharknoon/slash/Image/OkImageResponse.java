package de.sharknoon.slash.Image;

@SuppressWarnings({"FieldCanBeLocal", "UnusedParameters"})
public class OkImageResponse {
    private final String status;
    private final String imageID;

    public OkImageResponse(String status, String imageID) {
        this.status = status;
        this.imageID = imageID;
    }

    public String getImageID() {
        return imageID;
    }
}
