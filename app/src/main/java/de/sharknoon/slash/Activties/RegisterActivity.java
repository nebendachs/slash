package de.sharknoon.slash.Activties;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.sharknoon.slash.R;
import de.sharknoon.slash.Registration.UserRegistration;

public class RegisterActivity extends AppCompatActivity {

    private static final String VALID_EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Enable back arrow
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //   getSupportActionBar().setLogo(R.mipmap.ic_launcher_round);
        //   getSupportActionBar().setDisplayUseLogoEnabled(true);

        // Open Websocket connection
        UserRegistration.createRegistrationClient(this);
        this.handleRegisterButton();
    }

    private void handleRegisterButton() {

        // Get register button element
        Button registerButton = findViewById(R.id.registerScreenRegisterButton);

        // Set event listener
        registerButton.setOnClickListener(v -> {

            // Get input elements
            EditText usernameInput = findViewById(R.id.registerScreenUsernameInput);
            EditText emailInput = findViewById(R.id.registerScreenEmailInput);
            EditText passwordInput = findViewById(R.id.registerScreenPasswordInput);
            EditText confirmPasswordInput = findViewById(R.id.registerScreenConfirmPasswordInput);

            // Reset error message text viewer
            TextView usernameErrorTextView = findViewById(R.id.registerScreenUsernameErrorMessageTextView);
            usernameErrorTextView.setText("");
            TextView passwordErrorTextView = findViewById(R.id.registerScreenPasswordErrorMessageTextView);
            passwordErrorTextView.setText("");

            String insertedUsername = String.valueOf(usernameInput.getText());
            String insertedEmail = String.valueOf(emailInput.getText());
            String insertedPassword = String.valueOf(passwordInput.getText());
            String insertedConfirmPassword = String.valueOf(confirmPasswordInput.getText());

            // Check for empty username and e-mail
            if (insertedEmail.isEmpty() || insertedUsername.isEmpty()) {
                usernameErrorTextView.setText(R.string.registerScreenEmptyUsernameMessage);
                return;
            }

            //Check for false EMail
            if(insertedEmail.isEmpty() || !insertedEmail.matches(VALID_EMAIL_REGEX)){
                usernameErrorTextView.setText(R.string.registerScreenEmptyUsernameMessage);
                return;
            }

            // Check for empty password
            if(insertedPassword.isEmpty() || insertedConfirmPassword.isEmpty()){
                passwordErrorTextView.setText(R.string.registerScreenEmptyPasswordMessage);
                return;
            }

            // Check if passwords match
            if(!insertedPassword.equals(insertedConfirmPassword)){
                passwordErrorTextView.setText(R.string.registerScreenNoMatchPasswordMessage);
                return;
            }

            // Check for password guidelines
            if(!UserRegistration.checkForPasswordGuidelines(insertedPassword)){
                passwordErrorTextView.setText(R.string.registerScreenUnsafePasswordMessage);
                return;
            }

            // Send inserted username, email and password to server
            new UserRegistration(insertedUsername, insertedEmail, insertedPassword);
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
