package de.sharknoon.slash.HomeScreen;

import java.io.Serializable;
import java.util.List;

import de.sharknoon.slash.People.Person;

public class Project implements Serializable {

    private String id;
    private String name;
    private String description;
    private String image;
    private String creationDate;
    private List<Person> usernames;
    private String projectOwner;
    private Person.Sentiment sentiment;
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

    public List<Person> getUsernames() {
        return usernames;
    }

    public String getProjectOwner() {
        return projectOwner;
    }

    public Person.Sentiment getSentiment() {
        return sentiment;
    }

    public List<Chat.Message> getMessages() {
        return messages;
    }
}
