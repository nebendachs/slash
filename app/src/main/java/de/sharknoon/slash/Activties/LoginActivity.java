package de.sharknoon.slash.Activties;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.sharknoon.slash.Login.UserLogin;
import de.sharknoon.slash.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Open Websocket connection
        UserLogin.createLoginClient(this);

        this.handleRegisterLink();
        this.handleLoginStop();
        this.handleLoginButton();

        LoginActivity.disableLoadingScreen(true, this);
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

    private void handleLoginStop(){
        //Get progression bar to stop Login
        ProgressBar bar = findViewById(R.id.progressBar);
        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               LoginActivity.disableLoadingScreen(true, v.getContext());            }
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
                String insertedEmail = String.valueOf(emailInput.getText());

                // Get inserted Password
                EditText passwordInput = findViewById(R.id.homeScreenPasswordInput);
                String insertedPassword = String.valueOf(passwordInput.getText());


                boolean mailTrue = true;
                boolean passwordTrue = true;

                //False EMail
                if(insertedEmail.isEmpty()){
                    emailErrorTextView.setText(R.string.homeScreenIncorrectEmailMessage);
                    mailTrue = false;
                }

                // Empty password
                if (insertedPassword.isEmpty() || insertedPassword.length() < 8) {
                    passwordErrorTextView.setText(R.string.homeScreenIncorrectPasswordMessage);
                    passwordTrue = false;
                }

                // Try to login User
                if(mailTrue && passwordTrue) {
                    new UserLogin(insertedEmail, insertedPassword);
                    LoginActivity.disableLoadingScreen(false, v.getContext());
                }
            }
        });
    }

    public static void disableLoadingScreen(boolean b, Context context){
        RelativeLayout loadingScreen = ((Activity) context).findViewById(R.id.loadingScreen);
        EditText email = ((Activity) context).findViewById(R.id.homeScreenEmailInput);
        EditText password = ((Activity) context).findViewById(R.id.homeScreenPasswordInput);
        Button login = ((Activity) context).findViewById(R.id.homeScreenLoginButton);
        TextView register = ((Activity) context).findViewById(R.id.homeScreenRegisterLink);
        TextView forgotPW =((Activity) context).findViewById(R.id.homeScreenForgotPasswordLink);

        if(!b) {
            loadingScreen.setVisibility(View.VISIBLE);
        } else {
            loadingScreen.setVisibility(View.GONE);
        }
        email.setEnabled(b);
        password.setEnabled(b);
        login.setEnabled(b);
        register.setEnabled(b);
        forgotPW.setEnabled(b);
    }

}
