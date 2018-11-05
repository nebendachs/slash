package de.sharknoon.slash.HomeScreen;

public class Chat {

    private String id;
    private String personA;
    private String personB;
    private String personAUsername;
    private String personBUsername;
    private String creationDate;

    public Chat(String id, String personA, String personAUsername, String personB, String personBUsername, String creationDate){
        this.id = id;
        this.personA = personA;
        this.personAUsername = personAUsername;
        this.personB = personB;
        this.personBUsername = personBUsername;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public String getPersonA() {
        return personA;
    }

    public String getPersonB() {
        return personB;
    }

    public String getPersonAUsername() {
        return personAUsername;
    }

    public String getPersonBUsername() {
        return personBUsername;
    }

    public String getCreationDate() {
        return creationDate;
    }
}
