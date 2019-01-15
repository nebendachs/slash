package de.sharknoon.slash.Project;

import java.util.List;

public class AddProjectMessage {
    private String sessionid;
    private final String status = "ADD_PROJECT";
    private String projectName;
    private String projectDescription;
    private List<String> projectMembers;
    private String projectOwner;

    public AddProjectMessage(String sessionid, String projectName, String projectDescription, List<String> projectMembers, String projectOwner) {
        this.sessionid = sessionid;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectMembers = projectMembers;
        this.projectOwner = projectOwner;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getStatus() {
        return status;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public List<String> getProjectMembers() {
        return projectMembers;
    }

    public void setProjectMembers(List<String> projectMembers) {
        this.projectMembers = projectMembers;
    }

    public String getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(String projectOwner) {
        this.projectOwner = projectOwner;
    }
}
