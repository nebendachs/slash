package de.sharknoon.slash.People;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {
    public static final String MEMBER = "Team Member";
    public static final String SCRUM_MASTER = "Scrum Master";

    private String id;
    private String username;
    private String role;

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
}