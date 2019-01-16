package de.sharknoon.slash.Project;

public class UpdateScrumMasterMessage {
    private String sessionid;
    private final String status = "MODIFY_PROJECT_OWNER";
    private String projectID;
    private String projectOwner;

    public UpdateScrumMasterMessage(String sessionid, String projectID, String projectOwner) {
        this.sessionid = sessionid;
        this.projectID = projectID;
        this.projectOwner = projectOwner;
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

    public String getProjectOwner() {
        return projectOwner;
    }
}
