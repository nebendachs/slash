package de.sharknoon.slash.Project;

import java.util.List;

public class UpdateProjectMembersMessage {
    private final String sessionid;
    private final String status = "MODIFY_PROJECT_USERS";
    private final String projectID;
    private final List<String> users;
    private final boolean addUsers;

    public UpdateProjectMembersMessage(String sessionid, String projectID, List<String> users, boolean addUsers) {
        this.sessionid = sessionid;
        this.projectID = projectID;
        this.users = users;
        this.addUsers = addUsers;
    }
}
