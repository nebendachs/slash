package de.sharknoon.slash.Activties;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.ArrayList;

import de.sharknoon.slash.Fragments.CreateProject;
import de.sharknoon.slash.Fragments.PeopleSelector;
import de.sharknoon.slash.People.PeopleAdapter;
import de.sharknoon.slash.People.Person;
import de.sharknoon.slash.R;

public class AddPeopleActivity extends AppCompatActivity {
    private ArrayList<Person> selected;
    private PeopleAdapter adapter_selected;
    private ProjectPersonReceiver personReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        selected = new ArrayList<>();
        //ArrayList<Person> previous = new ArrayList<>();
        //previous.addAll((ArrayList<Person>)getIntent().getSerializableExtra("People"));
        selected.addAll((ArrayList<Person>)getIntent().getSerializableExtra("People"));

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = PeopleSelector.newInstance(PeopleSelector.PROJECT, selected);
        fragmentTransaction.add(R.id.add_people_selector_1, fragment);
        fragmentTransaction.commit();

        setContentView(R.layout.activity_add_people);

        personReceiver = new ProjectPersonReceiver();
        IntentFilter intentFilter = new IntentFilter(AddPeopleActivity.ProjectPersonReceiver.ACTION);
        registerReceiver(personReceiver, intentFilter);

        // Lookup the recyclerview in activity layout
        RecyclerView rvSelected = findViewById(R.id.selected_people);
        // Create adapter passing in the people list
        adapter_selected = new PeopleAdapter(selected, PeopleSelector.SELECTED);
        // Attach the adapter to the recyclerview to populate items
        rvSelected.setAdapter(adapter_selected);
        // Set layout manager to position the items
        //rvSelected.setLayoutManager(new GridLayoutManager(this, 4));
        rvSelected.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        this.handleCreateProjectButton();
    }

    private void handleCreateProjectButton() {
        Button button = findViewById(R.id.add_button);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(CreateProject.ProjectPeopleReveiver.ACTION);
            intent.putExtra(CreateProject.ProjectPeopleReveiver.ACTION, selected);
            sendBroadcast(intent);
            finish();
        });
    }

    public class ProjectPersonReceiver extends BroadcastReceiver {
        public static final String ACTION = "de.sharknoon.slash.RECEIVE_PERSON_PROJECT";

        @Override
        public void onReceive(Context context, Intent intent) {
            Person person = (Person) intent.getSerializableExtra("Person");
            selected.add(person);
            adapter_selected.notifyItemInserted(adapter_selected.getItemCount());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(personReceiver);
    }
}
