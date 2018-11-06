package de.sharknoon.slash.HomeScreen;

public class FindUser {
    private final String status = "GET_USER";
    private String username;

    public FindUser(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getStatus(){ return status;}
}