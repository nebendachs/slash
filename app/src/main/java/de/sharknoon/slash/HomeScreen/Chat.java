package de.sharknoon.slash.HomeScreen;

import java.util.List;

public class Chat {

    private String id;
    private String personA;
    private String personB;
    private String personAUsername;
    private String partnerUsername;
    private String creationDate;
    private List<Message> messages;

    public Chat(String id, String personA, String personAUsername, String personB, String partnerUsername, String creationDate, List<Message> messages){
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

    public List<Message> getMessages(){
        return messages;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public class Message{
        public String sender;
        public String creationDate;
        public String type;
        public String content;
        public String subject;
        public String emotion;
    }
}
