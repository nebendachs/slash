package de.sharknoon.slash.Image;

public class UpdateImageMessage {
    public static final String PROJECT = "MODIFY_PROJECT_IMAGE";
    public static final String USER = "MODIFY_USER_IMAGE";

    private String sessionid;
    private String status;
    private String projectID;
    private boolean remove;

    public UpdateImageMessage(String sessionid, String projectID, boolean remove) {
        this.sessionid = sessionid;
        this.status = PROJECT;
        this.projectID = projectID;
        this.remove = remove;
    }

    public UpdateImageMessage(String sessionid, boolean remove) {
        this.sessionid = sessionid;
        this.status = USER;
        this.remove = remove;
    }

    public String getSessionid() {
        return sessionid;
    }

    public String getStatus() {
        return status;
    }

    public String getProjectID() {
        return projectID;
    }

    public boolean isRemove() {
        return remove;
    }
}
