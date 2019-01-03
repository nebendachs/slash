package de.sharknoon.slash.People;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;

import java.io.Serializable;
import java.util.ArrayList;

import de.sharknoon.slash.R;

public class Person implements Serializable {
    private String username;
    private String id;
    private Bitmap picture;
    private int mood;

    public Person(String username, String id, Bitmap picture, int mood) {
        this.username = username;
        this.id = id;
        this.picture = picture;
        this.mood = mood;
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

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    /*private static int lastPersonId = 0;

    public static ArrayList<Person> createPeopleList(int numPeople, Context context) {
        ArrayList<Person> people = new ArrayList<Person>();

        for (int i = 1; i <= numPeople; i++) {
            people.add(new Person("Person " + ++lastPersonId, ""+lastPersonId, ((BitmapDrawable)ContextCompat.getDrawable(context, R.drawable.ic_person_fill)).getBitmap(), 5));
        }

        return people;
    }*/
}