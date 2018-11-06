package de.sharknoon.slash.HomeScreen;

public class HomeScreenResponse {

    private Chat chats [];
    private Project projects [];
    private String status;

    public HomeScreenResponse(Chat chats[], Project projects[], String status){
        this.chats = chats;
        this.projects = projects;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Chat[] getChats() {
        return chats;
    }

    public Project[] getProjects() {
        return projects;
    }
}
