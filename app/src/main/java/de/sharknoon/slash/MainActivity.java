package de.sharknoon.slash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.neovisionaries.ws.client.WebSocketException;

import java.io.IOException;

import de.sharknoon.slash.Server.Connector;

public class MainActivity extends AppCompatActivity {

    private Connector connector;
    private boolean hasConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView registerLink = findViewById(R.id.homeScreenRegisterLink);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegisterActivity = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(goToRegisterActivity);
            }
        });

        //Connect one time to server
        if(!hasConnected) {
            try {
                connector = new Connector();
            } catch (Exception e) {
                return;
            }
            hasConnected = true;
        }

        Button loginButton = findViewById(R.id.homeScreenLoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                TextView emailErrorTextView = findViewById(R.id.homeScreenWrongEmailTextView);
                emailErrorTextView.setText("");

                TextView passwordErrorTextView = findViewById(R.id.homeScreenWrongPasswordTextView);
                passwordErrorTextView.setText("");

                // Get inserted E-Mail
                EditText emailInput = findViewById(R.id.homeScreenEmailInput);
                String insertedEmail = emailInput.getText().toString();

                // Get inserted Password
                EditText passwordInput = findViewById(R.id.homeScreenPasswordInput);
                String insertedPassword = passwordInput.getText().toString();

                // Empty password
                if(insertedEmail.isEmpty()) {
                    emailErrorTextView.setText(R.string.homeScreenEmptyEmailMessage);
                    return;
                }

                // Empty password
                if (insertedPassword.isEmpty()){

                    passwordErrorTextView.setText(R.string.homeScreenIncorrectPasswordMessage);
                    return;
                }
                */

                // Try to login User
                new UserLogin("", "", connector);
            }
        });
    }
}
