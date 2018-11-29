package de.sharknoon.slash.Activties;

import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import de.sharknoon.slash.Fragments.PeopleSelector;
import de.sharknoon.slash.People.Person;
import de.sharknoon.slash.People.PeopleAdapter;
import de.sharknoon.slash.R;

public class AddPeopleActivity extends AppCompatActivity implements PeopleSelector.OnFragmentInteractionListener {
    ArrayList<Person> people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_people);

        Fragment peopleSelector = PeopleSelector.newInstance();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
