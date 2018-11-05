package de.sharknoon.slash.HomeScreen;

public class Project {

    private String id;
    private String name;
    private String image;
    private String creationDate;
    private String[] users;

    public Project(String id, String name, String image, String creationDate, String users[]){
        this.id = id;
        this.name = name;
        this.image = image;
        this.creationDate = creationDate;
        this.users = users;
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

    public String[] getUsers() {
        return users;
    }
}
