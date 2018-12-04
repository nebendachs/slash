package de.sharknoon.slash.HomeScreen;

import android.util.Log;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class Chat implements Serializable{

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

    public class Message implements Serializable, Comparable<Message> {

        public String sender;
        public String creationDate;
        public String type;
        public String content;
        public String subject;
        public String emotion;

        @Override
        public int compareTo(Message m) {
            if(creationDate.length() < 19){creationDate += ":00";}
            if(m.creationDate.length() < 19){m.creationDate += ":00";}
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.GERMANY);
            try {
                return format.parse(creationDate).compareTo(format.parse(m.creationDate));
            } catch (ParseException e){
                Log.i("Chat", "Parse Exception while parsing time");
                return 0;
            }

        }
    }
}
