package de.sharknoon.slash.HomeScreen;

import java.io.Serializable;
import java.util.List;

public class Project implements Serializable {

    private String id;
    private String name;
    private String image;
    private String creationDate;
    private List<String> users;
    private List<Chat.Message> messages;

    public Project(String id, String name, String image, String creationDate, List<String> users, List<Chat.Message> messages){
        this.id = id;
        this.name = name;
        this.image = image;
        this.creationDate = creationDate;
        this.users = users;
        this.messages = messages;
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

    public List<String> getUsers() {
        return users;
    }

    public List<Chat.Message> getMessages() { return messages; }
}
