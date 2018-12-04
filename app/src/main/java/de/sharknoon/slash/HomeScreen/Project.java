package de.sharknoon.slash.HomeScreen;

import java.io.Serializable;
import java.util.List;

public class Project implements Serializable {

    private String id;
    private String name;
    private String image;
    private String creationDate;
    private List<Username> usernames;
    private List<Chat.Message> messages;

    public Project(String id, String name, String image, String creationDate, List<Username> usernames, List<Chat.Message> messages){
        this.id = id;
        this.name = name;
        this.image = image;
        this.creationDate = creationDate;
        this.messages = messages;
        this.usernames = usernames;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public List<Chat.Message> getMessages() { return messages; }

    public List<Username> getUsernames() {
        return usernames;
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
