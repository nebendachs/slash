package de.sharknoon.slash.Activties;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import de.sharknoon.slash.ChatMessages.UserCreateClientOrProjekt;
import de.sharknoon.slash.R;

public class CreateClientProjektActivity extends AppCompatActivity {

    private UserCreateClientOrProjekt ccp;
    private int state = 0;                      //0 = chat, 1 = project

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chat_projekt);

        ccp = new UserCreateClientOrProjekt();

        this.searchForUser();

        Button chatButton = findViewById(R.id.createChatProjectButtonChat);
        Button projectButton = findViewById(R.id.createChatProjectButtonProject);

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                changeScreen(v.getContext());
            }
        });

        projectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                changeScreen(v.getContext());
            }
        });

        this.changeScreen(this);
    }

    private void searchForUser() {

        // Get text view element for registration and handover event listener
        EditText searchWindow = findViewById(R.id.createChatEditWindow);
        Button searchButton = findViewById(R.id.createChatFindButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Search for User
                ccp.searchSinglePerson(v.getContext(), searchWindow.getText().toString());
            }
        });
    }

    public void changeScreen(Context context){
        RelativeLayout chatLayout = findViewById(R.id.createChatScreen);
        RelativeLayout projectLayout = findViewById(R.id.createProjectScreen);
        Button chatButton = findViewById(R.id.createChatProjectButtonChat);
        Button projectButton = findViewById(R.id.createChatProjectButtonProject);

        if(state == 0){
            chatButton.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            projectButton.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
            chatLayout.setVisibility(View.VISIBLE);
            projectLayout.setVisibility(View.GONE);
            state = 1;
        } else if(state == 1){
            chatButton.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
            projectButton.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            chatLayout.setVisibility(View.GONE);
            projectLayout.setVisibility(View.VISIBLE);
            state = 0;
        }
    }
}
