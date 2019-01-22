package de.sharknoon.slash.HomeScreen;

@SuppressWarnings({"FieldCanBeLocal", "UnusedParameters"})
public class FindUser {
    private final String status = "GET_USERS";
    private final String search;
    private final String sessionid;

    public FindUser(String sessionid, String search){
        this.search = search;
        this.sessionid = sessionid;
    }
}