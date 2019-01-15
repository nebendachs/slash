package de.sharknoon.slash.People;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
    public static final String MEMBER = "Developer";
    public static final String SCRUM_MASTER = "Scrum Master";

    public static final String POSITIVE = "POSITIVE";
    public static final String NEUTRAL = "NEUTRAL";
    public static final String NEGATIVE = "NEGATIVE";

    private String id;
    private String username;
    private String role;
    private Sentiment sentiment;

    public Person(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Sentiment getSentiment() {
        return sentiment;
    }

    public class Sentiment implements Serializable {
        private String polarity;
        private String subjectivity;

        public String getPolarity() {
            return polarity;
        }

        public String getSubjectivity() {
            return subjectivity;
        }
    }
}