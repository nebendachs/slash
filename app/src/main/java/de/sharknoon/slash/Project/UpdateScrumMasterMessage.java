package de.sharknoon.slash.Project;

@SuppressWarnings({"FieldCanBeLocal", "UnusedParameters"})
public class UpdateScrumMasterMessage {
    private final String sessionid;
    private final String status = "MODIFY_PROJECT_OWNER";
    private final String projectID;
    private final String projectOwner;

    public UpdateScrumMasterMessage(String sessionid, String projectID, String projectOwner) {
        this.sessionid = sessionid;
        this.projectID = projectID;
        this.projectOwner = projectOwner;
    }
}
