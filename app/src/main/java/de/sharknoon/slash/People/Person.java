package de.sharknoon.slash.People;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {
    private String username;
    private String id;

    public Person(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private static int lastPersonId = 0;

    public static ArrayList<Person> createPeopleList(int numPeople) {
        ArrayList<Person> people = new ArrayList<Person>();

        for (int i = 1; i <= numPeople; i++) {
            people.add(new Person("Person " + ++lastPersonId));
        }

        return people;
    }
}