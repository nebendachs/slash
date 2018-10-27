package de.sharknoon.slash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.handleRegisterLink();
        this.handleLoginButton();
    }


    private void handleRegisterLink() {

        // Get text view element for registration and handover event listener
        TextView registerLink = findViewById(R.id.homeScreenRegisterLink);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Move on to registration page
                Intent goToRegisterActivity = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(goToRegisterActivity);
            }
        });
    }

    private void handleLoginButton() {

        // Get login button element and handover event listener
        Button loginButton = findViewById(R.id.homeScreenLoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Reset error message text viewer
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
                if (insertedEmail == null || insertedEmail.isEmpty()) {
                    emailErrorTextView.setText(R.string.homeScreenEmptyEmailMessage);
                    return;
                }

                // Empty password
                if (insertedPassword == null || insertedPassword.isEmpty()) {
                    passwordErrorTextView.setText(R.string.homeScreenIncorrectPasswordMessage);
                    return;
                }

                // Try to login User
                new UserLogin(insertedEmail, insertedPassword);
            }
        });
    }
}
