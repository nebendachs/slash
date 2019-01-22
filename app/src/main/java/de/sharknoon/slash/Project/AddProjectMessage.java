package de.sharknoon.slash.Project;

import java.util.List;

@SuppressWarnings({"FieldCanBeLocal", "UnusedParameters"})
public class AddProjectMessage {
    private final String sessionid;
    private final String status = "ADD_PROJECT";
    private final String projectName;
    private final String projectDescription;
    private final List<String> projectMembers;
    private final String projectOwner;

    public AddProjectMessage(String sessionid, String projectName, String projectDescription, List<String> projectMembers, String projectOwner) {
        this.sessionid = sessionid;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectMembers = projectMembers;
        this.projectOwner = projectOwner;
    }
}
