package de.sharknoon.slash.People;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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