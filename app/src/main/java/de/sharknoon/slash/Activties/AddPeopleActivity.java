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
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import de.sharknoon.slash.Fragments.CreateProject;
import de.sharknoon.slash.Fragments.PeopleSelector;
import de.sharknoon.slash.People.PeopleAdapter;
import de.sharknoon.slash.People.Person;
import de.sharknoon.slash.Project.UpdateProjectMembersMessage;
import de.sharknoon.slash.R;
import de.sharknoon.slash.SharedPreferences.ParameterManager;

import static de.sharknoon.slash.HomeScreen.UserHomeScreen.homeScreenClient;

public class AddPeopleActivity extends AppCompatActivity {
    public static String PEOPLE = "people";
    public static String IS_NEW_PROJECT = "isNewProject";

    private ArrayList<Person> selected;
    private PeopleAdapter adapter_selected;
    private ProjectPersonReceiver personReceiver = null;
    private boolean isNewProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isNewProject = getIntent().getBooleanExtra(IS_NEW_PROJECT, true);
        selected = new ArrayList<>((ArrayList<Person>)getIntent().getSerializableExtra(PEOPLE));

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = PeopleSelector.newInstance(PeopleSelector.PROJECT, new ArrayList<Person>(selected));
        fragmentTransaction.add(R.id.add_people_selector_1, fragment);
        fragmentTransaction.commit();

        if(!isNewProject)
            selected.clear();

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
            if(isNewProject) {
                Intent intent = new Intent(CreateProject.ProjectPeopleReveiver.ACTION);
                intent.putExtra(CreateProject.ProjectPeopleReveiver.ACTION, selected);
                sendBroadcast(intent);
            } else if(selected.size() > 0) {
                ArrayList<String> users = new ArrayList<>();
                selected.forEach(person -> users.add(person.getId()));
                Gson gson = new Gson();
                UpdateProjectMembersMessage updateProjectMembersMessage = new UpdateProjectMembersMessage(
                        ParameterManager.getSession(this),
                        ParameterManager.getCurrentOpenChatOrProject().getProject().getId(),
                        users,
                        true
                );
                String jsonUpdateProjectMembersMessage = gson.toJson(updateProjectMembersMessage);
                Log.i("XXXXXX", jsonUpdateProjectMembersMessage);
                if (homeScreenClient != null) {
                    homeScreenClient.getWebSocketClient().send(jsonUpdateProjectMembersMessage);
                    //update project member list in project info activity
                    Log.d("asdf", selected.toString());
                    Intent intent = new Intent(ProjectInfoActivity.ProjectPeopleAddedReceiver.ACTION);
                    intent.putExtra(ProjectInfoActivity.ProjectPeopleAddedReceiver.ACTION, selected);
                    sendBroadcast(intent); } else Toast.makeText(this, getString(R.string.error_socket_not_connected), Toast.LENGTH_LONG).show();
            }
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
