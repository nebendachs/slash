package de.sharknoon.slash.Image;

public class OkImageResponse {
    String status;
    String imageID;

    public OkImageResponse(String status, String imageID) {
        this.status = status;
        this.imageID = imageID;
    }

    public String getStatus() {
        return status;
    }

    public String getImageID() {
        return imageID;
    }
}
