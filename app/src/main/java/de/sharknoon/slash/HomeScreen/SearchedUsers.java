package de.sharknoon.slash.HomeScreen;

import java.util.List;

public class SearchedUsers {

    private String status;
    private List<User> users;

    public SearchedUsers(String status, List<User> users){
        this.status = status;
        this.users = users;
    }

    public String getStatus() { return status; }

    public class User {

        String id;
        String username;
    }
}
