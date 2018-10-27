package de.sharknoon.slash;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

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

                String insertedUsername = usernameInput.getText().toString();
                String insertedEmail = emailInput.getText().toString();
                String insertedPassword = passwordInput.getText().toString();
                String insertedConfirmPassword = confirmPasswordInput.getText().toString();

                // Check for empty username and e-mail
                if (insertedEmail == null || insertedEmail.isEmpty() || insertedUsername == null || insertedUsername.isEmpty()) {
                    usernameErrorTextView.setText(R.string.registerScreenEmptyUsernameMessage);
                    return;
                }

                // Check for empty password
                if(insertedPassword == null || insertedPassword.isEmpty() || insertedConfirmPassword == null || insertedConfirmPassword.isEmpty()){
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

                new UserRegistration(insertedUsername, insertedEmail, insertedPassword);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
