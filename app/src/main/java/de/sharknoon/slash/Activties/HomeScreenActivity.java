package de.sharknoon.slash.Activties;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import de.sharknoon.slash.HomeScreen.UserHomeScreen;
import de.sharknoon.slash.Login.LoginResponseHandler;
import de.sharknoon.slash.R;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_scree);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Get the session id from Intent
        Bundle bundle = getIntent().getExtras();
        String sessionId = bundle.getString(LoginResponseHandler.BUNDLE_KEY_SESSION_ID);
        Log.d("SessionId", sessionId);
        new UserHomeScreen(sessionId, this);
    }
}
