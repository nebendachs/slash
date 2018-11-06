package de.sharknoon.slash.HomeScreen;

public class UserResponse {

    private String status;
    private String userId;
    private String username;

    public UserResponse(String status, String userId, String username){
        this.userId = userId;
        this.username = username;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getUserID() {
        return userId;
    }

    public String getUsername() {
        return username;
    }
}
