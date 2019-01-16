package de.sharknoon.slash.Activties;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import de.hdodenhof.circleimageview.CircleImageView;
import de.sharknoon.slash.ChatMessages.ImageLoader;
import de.sharknoon.slash.Fragments.PeopleSelector;
import de.sharknoon.slash.HomeScreen.Project;
import de.sharknoon.slash.Image.ImageSender;
import de.sharknoon.slash.Image.SentimentLoader;
import de.sharknoon.slash.People.PeopleAdapter;
import de.sharknoon.slash.People.Person;
import de.sharknoon.slash.R;

public class ProjectInfoActivity extends AppCompatActivity {
    private Project project;
    private CircleImageView projectImage;
    private ImageView projectMood;
    private TextView projectName;
    private TextView projectDesc;
    private PeopleAdapter adapter;
    private ArrayList<Person> members;

    private int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_info);

        projectImage = findViewById(R.id.element_picture);
        projectMood = findViewById(R.id.element_mood);
        projectName = findViewById(R.id.info_project_name);
        projectDesc = findViewById(R.id.info_project_description);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        project = (Project)bundle.getSerializable(ChatScreenActivity.PROJECT);

        getSupportActionBar().setTitle(project.getName());

        //set project image
        if(project.getImage() != null)
            new ImageLoader(project.getImage(), this, projectImage);
        else
            projectImage.setImageResource(R.drawable.logo);
        projectImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                // Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, getString(R.string.activity_chat_screen_select_image)), PICK_IMAGE_REQUEST);
            }
        });

        new SentimentLoader(project.getSentiment(), projectMood);

        projectName.setText(project.getName());
        projectDesc.setText(project.getDescription());

        members = new ArrayList<>();
        members.addAll(project.getUsernames());
        for(int i=0; i<members.size(); i++) {
            if(members.get(i).getId().equals(project.getProjectOwner()))
                members.get(i).setRole(Person.SCRUM_MASTER);
            else
                members.get(i).setRole(Person.MEMBER);
        }
        members.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getUsername().compareToIgnoreCase(o2.getUsername());
            }
        });
        //todo: Start chat on click
        //todo: Change scrum master on click

        RecyclerView recyclerView = findViewById(R.id.info_project_members);
        adapter = new PeopleAdapter(members, PeopleSelector.PROJECT_INFO);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                projectImage.setImageBitmap(bitmap);
                new ImageSender(bitmap, this, ImageSender.PROJECT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
