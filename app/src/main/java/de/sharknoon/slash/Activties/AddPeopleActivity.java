package de.sharknoon.slash.Activties;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import de.sharknoon.slash.Fragments.PeopleSelector;
import de.sharknoon.slash.Fragments.Profile;
import de.sharknoon.slash.People.Person;
import de.sharknoon.slash.People.PeopleAdapter;
import de.sharknoon.slash.R;

public class AddPeopleActivity extends AppCompatActivity {
    private ArrayList<Person> people;
    private ArrayList<Person> selected;
    private PeopleAdapter adapter_selected;
    private ProjectPersonReceiver personReceiver = null;
    private PeopleSelector peopleSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = PeopleSelector.newInstance(PeopleSelector.PROJECT);
        fragmentTransaction.add(R.id.add_people_selector_1, fragment);
        fragmentTransaction.commit();

        setContentView(R.layout.activity_add_people);

        personReceiver = new ProjectPersonReceiver();
        personReceiver.setActivity(this);
        IntentFilter intentFilter = new IntentFilter(AddPeopleActivity.ProjectPersonReceiver.ACTION);
        registerReceiver(personReceiver, intentFilter);

        selected = new ArrayList<>();
        // Lookup the recyclerview in activity layout
        RecyclerView rvSelected = findViewById(R.id.selected_people);
        // Create adapter passing in the people list
        adapter_selected = new PeopleAdapter(selected, PeopleSelector.SELECTED);
        // Attach the adapter to the recyclerview to populate items
        rvSelected.setAdapter(adapter_selected);
        // Set layout manager to position the items
        rvSelected.setLayoutManager(new GridLayoutManager(this, 3));

        this.handleCreateProjectButton();
    }

    private void handleCreateProjectButton() {
        Button button = findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo create project
            }
        });
    }

    public class ProjectPersonReceiver extends BroadcastReceiver {
        AddPeopleActivity activity = null;
        public static final String ACTION = "de.sharknoon.slash.RECEIVE_PERSON_PROJECT";

        @Override
        public void onReceive(Context context, Intent intent) {
            Person person = (Person) intent.getSerializableExtra("Person");
            selected.add(person);
            adapter_selected.notifyItemInserted(adapter_selected.getItemCount());
        }

        public void setActivity(AddPeopleActivity activity) {
            this.activity = activity;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(personReceiver);
    }
}
