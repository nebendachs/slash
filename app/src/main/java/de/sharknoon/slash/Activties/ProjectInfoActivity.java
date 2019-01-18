package de.sharknoon.slash.Activties;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

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
    private CircleImageView projectImage;

    private final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_info);

        projectImage = findViewById(R.id.element_picture);
        ImageView projectMood = findViewById(R.id.element_mood);
        TextView projectName = findViewById(R.id.info_project_name);
        TextView projectDesc = findViewById(R.id.info_project_description);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        Project project = (Project) bundle.getSerializable(ChatScreenActivity.PROJECT);

        getSupportActionBar().setTitle(project.getName());

        //set project image
        if(project.getImage() != null)
            new ImageLoader(project.getImage(), this, projectImage);
        else
            projectImage.setImageResource(R.drawable.logo);
        projectImage.setOnClickListener(v -> {
            Intent intent1 = new Intent();
            // Show only images, no videos or anything else
            intent1.setType("image/*");
            intent1.setAction(Intent.ACTION_GET_CONTENT);
            // Always show the chooser (if there are multiple options available)
            startActivityForResult(Intent.createChooser(intent1, getString(R.string.activity_chat_screen_select_image)), PICK_IMAGE_REQUEST);
        });

        new SentimentLoader(project.getSentiment(), projectMood);

        projectName.setText(project.getName());
        projectDesc.setText(project.getDescription());

        ArrayList<Person> members = new ArrayList<>(project.getUsernames());
        for(int i = 0; i< members.size(); i++) {
            if(members.get(i).getId().equals(project.getProjectOwner()))
                members.get(i).setRole(Person.SCRUM_MASTER);
            else
                members.get(i).setRole(Person.MEMBER);
        }
        members.sort((o1, o2) -> o1.getUsername().compareToIgnoreCase(o2.getUsername()));

        RecyclerView recyclerView = findViewById(R.id.info_project_members);
        PeopleAdapter adapter = new PeopleAdapter(members, PeopleSelector.PROJECT_INFO);
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
