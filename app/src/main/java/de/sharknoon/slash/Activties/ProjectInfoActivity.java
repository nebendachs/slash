package de.sharknoon.slash.Activties;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

import de.sharknoon.slash.Fragments.PeopleSelector;
import de.sharknoon.slash.HomeScreen.Project;
import de.sharknoon.slash.People.PeopleAdapter;
import de.sharknoon.slash.People.Person;
import de.sharknoon.slash.R;

public class ProjectInfoActivity extends AppCompatActivity {
    private Project project;
    private ImageView projectImage;
    private TextView projectName;
    private TextView projectDesc;
    private PeopleAdapter adapter;
    private ArrayList<Person> members;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_info);

        projectImage = findViewById(R.id.info_project_image);
        projectName = findViewById(R.id.info_project_name);
        projectDesc = findViewById(R.id.info_project_description);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        project = (Project)bundle.getSerializable(ChatScreenActivity.PROJECT);

        getSupportActionBar().setTitle(project.getName());

        //todo: set project image
        projectName.setText(project.getName());
        projectDesc.setText(project.getDescription());

        members = new ArrayList<>();
        for(int i=0; i<project.getUsernames().size(); i++) {
            Project.Username user = project.getUsernames().get(i);
            Person person = new Person(user.getId(), user.getUsername());
            if(person.getId().equals(project.getProjectOwner()))
                person.setRole(Person.SCRUM_MASTER);
            else
                person.setRole(Person.MEMBER);
            members.add(person);
        }
        members.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getUsername().compareToIgnoreCase(o2.getUsername());
            }
        });

        RecyclerView recyclerView = findViewById(R.id.info_project_members);
        adapter = new PeopleAdapter(members, PeopleSelector.PROJECT_INFO);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
