package de.sharknoon.slash.Project;

public class GetProjectMessage {
    private String sessionid;
    private final String status = "GET_PROJECT";
    private String projectID;

    public GetProjectMessage(String sessionid, String projectID) {
        this.sessionid = sessionid;
        this.projectID = projectID;
    }

    public String getSessionid() {
        return sessionid;
    }

    public String getProjectID() {
        return projectID;
    }
}
