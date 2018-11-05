package de.sharknoon.slash.HomeScreen;

public class Chat {

    private String id;
    private String personA;
    private String personB;
    private String nameA;
    private String nameB;
    private String creationDate;

    public Chat(String id, String personA, String nameA, String personB, String nameB, String creationDate){
        this.id = id;
        this.personA = personA;
        this.nameA = nameA;
        this.personB = personB;
        this.nameB = nameB;
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

    public String getNameA() {
        return nameA;
    }

    public String getNameB() {
        return nameB;
    }

    public String getCreationDate() {
        return creationDate;
    }
}
