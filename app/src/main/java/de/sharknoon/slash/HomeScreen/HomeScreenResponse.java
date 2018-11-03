package de.sharknoon.slash.HomeScreen;

public class HomeScreenResponse {

    private Contact contacts [];
    private Contact projects [];
    private String status;
    private String message;
    private String sessionid;

    public HomeScreenResponse(Contact contacts[], Contact projects[], String status, String message, String sessionid){
        this.contacts = contacts;
        this.projects = projects;
        this.status = status;
        this.message = message;
        this.sessionid = sessionid;
    }

    public Contact[] getContacts() {
        return contacts;
    }

    public Contact[] getProjects() {
        return projects;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getSessionid() {
        return sessionid;
    }
}
