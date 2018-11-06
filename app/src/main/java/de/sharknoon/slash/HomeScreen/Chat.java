package de.sharknoon.slash.HomeScreen;

public class Chat {

    private String id;
    private String personA;
    private String personB;
    private String personAUsername;
    private String partnerUsername;
    private String creationDate;
    private String[] messages;

    public Chat(String id, String personA, String personAUsername, String personB, String partnerUsername, String creationDate, String[] messages){
        this.id = id;
        this.personA = personA;
        this.personAUsername = personAUsername;
        this.personB = personB;
        this.partnerUsername = partnerUsername;
        this.creationDate = creationDate;
        this.messages = messages;
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

    public String getPartnerUsername() {
        return partnerUsername;
    }

    public String[] getMessages(){
        return messages;
    }

    public String getCreationDate() {
        return creationDate;
    }
}
