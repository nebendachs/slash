package de.sharknoon.slash.HomeScreen;

import java.io.Serializable;
import java.util.List;

public class Project implements Serializable {

    private String id;
    private String name;
    private String description;
    private String image;
    private String creationDate;
    private List<Username> usernames;
    private String projectOwner;
    private List<Chat.Message> messages;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public List<Username> getUsernames() {
        return usernames;
    }

    public String getProjectOwner() {
        return projectOwner;
    }

    public List<Chat.Message> getMessages() {
        return messages;
    }

    public class Username implements Serializable{
        private String id;
        private String username;

        public Username(String id, String username){
            this.id = id;
            this.username = username;
        }

        public String getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }
    }
}
