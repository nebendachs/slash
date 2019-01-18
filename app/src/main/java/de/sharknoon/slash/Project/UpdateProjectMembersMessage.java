package de.sharknoon.slash.Project;

public class UpdateProjectMembersMessage {
    private final String sessionid;
    private final String status = "MODIFY_PROJECT_USERS";
    private final String projectID;
    private final String userID;
    private final boolean addUser;

    public UpdateProjectMembersMessage(String sessionid, String projectID, String userID, boolean addUser) {
        this.sessionid = sessionid;
        this.projectID = projectID;
        this.userID = userID;
        this.addUser = addUser;
    }
}
