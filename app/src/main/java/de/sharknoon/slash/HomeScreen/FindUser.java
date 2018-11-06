package de.sharknoon.slash.HomeScreen;

public class FindUser {
    private final String status = "GET_USER";
    private String username;
    private String sessionid;

    public FindUser(String sessionid, String username){
        this.username = username;
        this.sessionid = sessionid;
    }

    public String getUsername() {
        return username;
    }

    public String getStatus(){ return status;}

    public String getSessionId(){ return sessionid; }
}