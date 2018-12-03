package de.sharknoon.slash.Activties;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;

import de.sharknoon.slash.Fragments.PeopleSelector;
import de.sharknoon.slash.Fragments.Profile;
import de.sharknoon.slash.People.Person;
import de.sharknoon.slash.People.PeopleAdapter;
import de.sharknoon.slash.R;

public class AddPeopleActivity extends AppCompatActivity {
    ArrayList<Person> people;
    private ProjectPersonReceiver personReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_people);

        personReceiver = new ProjectPersonReceiver();
        personReceiver.setActivity(this);
        IntentFilter intentFilter = new IntentFilter(AddPeopleActivity.ProjectPersonReceiver.ACTION);
        registerReceiver(personReceiver, intentFilter);

        //todo add button onClick Event that creates the project with the selected people

        Fragment peopleSelector = PeopleSelector.newInstance("Project");
    }

    public class ProjectPersonReceiver extends BroadcastReceiver {
        AddPeopleActivity activity = null;
        public static final String ACTION = "de.sharknoon.slash.RECEIVE_PERSON_PROJECT";

        @Override
        public void onReceive(Context context, Intent intent) {
            Person person = (Person) intent.getSerializableExtra("Person");
            //todo Person zu RecyclerView hinzufügen
        }

        public void setActivity(AddPeopleActivity activity) {
            this.activity = activity;
        }
    }
}
