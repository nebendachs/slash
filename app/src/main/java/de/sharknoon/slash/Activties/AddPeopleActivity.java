package de.sharknoon.slash.Activties;

import android.os.Bundle;
import android.provider.Contacts;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import de.sharknoon.slash.People.Person;
import de.sharknoon.slash.People.PeopleAdapter;
import de.sharknoon.slash.R;

public class AddPeopleActivity extends AppCompatActivity {
    ArrayList<Person> people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_people);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        // Lookup the recyclerview in activity layout
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.people_list);

        // Initialize contacts
        people = Person.createPeopleList(20);
        // Create adapter passing in the sample user data
        PeopleAdapter adapter = new PeopleAdapter(people);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
    }
}
