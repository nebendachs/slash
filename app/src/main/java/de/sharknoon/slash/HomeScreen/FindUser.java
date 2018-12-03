package de.sharknoon.slash.HomeScreen;

public class FindUser {
    private final String status = "GET_USERS";
    private String search;
    private String sessionid;

    public FindUser(String sessionid, String search){
        this.search = search;
        this.sessionid = sessionid;
    }

    public String getSearch() {
        return search;
    }

    public String getStatus(){ return status;}

    public String getSessionId(){ return sessionid; }
}