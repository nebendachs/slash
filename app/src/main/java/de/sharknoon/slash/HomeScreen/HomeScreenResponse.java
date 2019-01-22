package de.sharknoon.slash.HomeScreen;

@SuppressWarnings({"FieldCanBeLocal", "UnusedParameters"})
class HomeScreenResponse {
    private final Chat[] chats ;
    private final Project[] projects ;
    private final String status;

    public HomeScreenResponse(Chat chats[], Project projects[], String status){
        this.chats = chats;
        this.projects = projects;
        this.status = status;
    }

    public Chat[] getChats() {
        return chats;
    }

    public Project[] getProjects() {
        return projects;
    }
}
