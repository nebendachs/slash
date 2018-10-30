package de.sharknoon.slash.Activties;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.sharknoon.slash.R;
import de.sharknoon.slash.Registration.UserRegistration;

public class RegisterActivity extends AppCompatActivity {

    private UserRegistration userRegistration = null;

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
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
