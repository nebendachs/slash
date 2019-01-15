package de.sharknoon.slash.HomeScreen;

import java.io.Serializable;
import java.util.List;

import de.sharknoon.slash.People.Person;

public class PersonSearchResult implements Serializable {

    private List<Person> users;

    public List<Person> getUsers() {
        return users;
    }

    public void setUsers(List<Person> users) {
        this.users = users;
    }
}
