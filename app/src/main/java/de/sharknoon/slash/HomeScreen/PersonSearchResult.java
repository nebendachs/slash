package de.sharknoon.slash.HomeScreen;

import java.io.Serializable;
import java.util.List;

import de.sharknoon.slash.People.Person;

@SuppressWarnings({"FieldCanBeLocal", "UnusedParameters"})
public class PersonSearchResult implements Serializable {

    private List<Person> users;

    public List<Person> getUsers() {
        return users;
    }
}
