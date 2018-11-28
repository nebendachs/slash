package de.sharknoon.slash.HomeScreen;

public class SearchedUsers {

    private String status;
    private user[] users;

    public SearchedUsers(String status, user[] users){
        this.status = status;
        this.users = users;
    }

    public String getStatus() { return status; }

    public user[] getUsers(){ return  users; }

    public class user{

        String id;
        String username;

        public user(String id, String username) {
            this.id = id;
            this.username = username;
        }

        public String getId(){ return id; }

        public String getUsername(){ return username; }
    }
}
