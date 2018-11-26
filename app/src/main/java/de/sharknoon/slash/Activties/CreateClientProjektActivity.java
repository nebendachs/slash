package de.sharknoon.slash.Activties;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.sharknoon.slash.HomeScreen.UserCreateClientOrProjekt;
import de.sharknoon.slash.R;

import static de.sharknoon.slash.HomeScreen.UserHomeScreen.homeScreenClient;
import static de.sharknoon.slash.HomeScreen.UserHomeScreen.sessionId;

public class CreateClientProjektActivity extends AppCompatActivity {

    private UserCreateClientOrProjekt ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chat_projekt);

        ccp = new UserCreateClientOrProjekt();

        this.searchForUser();
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
}
