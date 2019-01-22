package de.sharknoon.slash.HomeScreen;

import android.util.Log;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import de.sharknoon.slash.People.Person;

@SuppressWarnings({"FieldCanBeLocal", "UnusedParameters"})
public class Chat implements Serializable {
    private final String id;
    private final String personA;
    private final String personB;
    private final String partnerUsername;
    private final String partnerImage;
    private final String creationDate;
    private final Person.Sentiment partnerSentiment;
    private final List<Message> messages;

    public Chat(String id, String personA, String personB, String partnerUsername, String partnerImage, String creationDate, Person.Sentiment partnerSentiment, List<Message> messages){
        this.id = id;
        this.personA = personA;
        this.personB = personB;
        this.partnerUsername = partnerUsername;
        this.partnerImage = partnerImage;
        this.creationDate = creationDate;
        this.partnerSentiment = partnerSentiment;
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

    public String getPartnerUsername() {
        return partnerUsername;
    }

    public String getPartnerImage() {
        return partnerImage;
    }

    public List<Message> getMessages(){
        return messages;
    }

    public Person.Sentiment getSentiment() {
        return partnerSentiment;
    }

    public class Message implements Serializable, Comparable<Message> {

        public String sender;
        public String creationDate;
        public String type;
        public String content;
        public String subject;
        public String emotion;
        public String image;

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
