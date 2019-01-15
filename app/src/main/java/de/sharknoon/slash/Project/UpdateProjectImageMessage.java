package de.sharknoon.slash.Project;

public class UpdateProjectImageMessage {
    private String sessionid;
    private final String status = "MODIFY_PROJECT_IMAGE";
    private String projectID;
    private boolean remove;

    public UpdateProjectImageMessage(String sessionid, String projectID, boolean remove) {
        this.sessionid = sessionid;
        this.projectID = projectID;
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
