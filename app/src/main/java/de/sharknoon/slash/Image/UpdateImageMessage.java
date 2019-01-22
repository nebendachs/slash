package de.sharknoon.slash.Image;

@SuppressWarnings({"FieldCanBeLocal", "UnusedParameters"})
class UpdateImageMessage {
    private static final String PROJECT = "MODIFY_PROJECT_IMAGE";
    private static final String USER = "MODIFY_USER_IMAGE";

    private final String sessionid;
    private final String status;
    private String projectID;
    private final boolean remove;

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
}
