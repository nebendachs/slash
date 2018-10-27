package de.sharknoon.slash;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
     //   getSupportActionBar().setLogo(R.mipmap.ic_launcher_round);
     //   getSupportActionBar().setDisplayUseLogoEnabled(true);

        EditText usernameInput = findViewById(R.id.registerScreenUsernameInput);
        EditText emailInput = findViewById(R.id.registerScreenEmailInput);
        EditText passwordInput = findViewById(R.id.registerScreenPasswordInput);
        EditText confirmPasswordInput = findViewById(R.id.registerScreenEmailInput);

     //   TextView unsernameErrorTextView =

        String insertedUsername = usernameInput.getText().toString();
        String insertedEmail = emailInput.getText().toString();
        String insertedPassword = passwordInput.getText().toString();
        String insertedConfirmPassword = confirmPasswordInput.getText().toString();

        if(insertedEmail == null || insertedEmail.isEmpty() || insertedUsername == null || insertedUsername.isEmpty()){



        }


    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
}
