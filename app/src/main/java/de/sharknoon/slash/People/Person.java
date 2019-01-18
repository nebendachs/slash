package de.sharknoon.slash.People;

import android.support.annotation.NonNull;

import java.io.Serializable;

@SuppressWarnings({"FieldCanBeLocal", "UnusedParameters"})
public class Person implements Serializable {
    public static final String MEMBER = "Developer";
    public static final String SCRUM_MASTER = "Scrum Master";

    public static final String POSITIVE = "POSITIVE";
    public static final String NEUTRAL = "NEUTRAL";
    public static final String NEGATIVE = "NEGATIVE";

    private final String id;
    private final String username;
    private String role;
    private String image;
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

    public String getImage() {
        return image;
    }

    public Sentiment getSentiment() {
        return sentiment;
    }

    @NonNull
    public String toString() {
        if(username != null)
            return username;
        return "Unknown User";
    }

    public class Sentiment implements Serializable {
        private String polarity;
        private String subjectivity;

        public String getPolarity() {
            return polarity;
        }

    }
}