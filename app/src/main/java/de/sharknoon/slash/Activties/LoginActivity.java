package de.sharknoon.slash.Activties;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.concurrent.CompletableFuture;

import de.sharknoon.slash.Login.UserLogin;
import de.sharknoon.slash.R;
import de.sharknoon.slash.SharedPreferences.ParameterManager;
import me.pushy.sdk.Pushy;
import me.pushy.sdk.util.exceptions.PushyException;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pushy.listen(this);

        // Check whether the user has granted us the READ/WRITE_EXTERNAL_STORAGE permissions
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Request both READ_EXTERNAL_STORAGE and WRITE_EXTERNAL_STORAGE so that the
            // Pushy SDK will be able to persist the device token in the external storage
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        if(ParameterManager.getSession(this) != null) {
            Intent goToHomeScreenActivity = new Intent(getApplicationContext(), HomeScreenActivity.class);
            startActivity(goToHomeScreenActivity);
        }

        setContentView(R.layout.activity_login);

        // Open Websocket connection
        UserLogin.createLoginClient(this);

        this.handleRegisterLink();
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
                if (insertedEmail.isEmpty()) {
                    emailErrorTextView.setText(R.string.homeScreenIncorrectEmailMessage);
                    mailTrue = false;
                }

                // Empty password
                if (insertedPassword.isEmpty() || insertedPassword.length() < 8) {
                    passwordErrorTextView.setText(R.string.homeScreenIncorrectPasswordMessage);
                    passwordTrue = false;
                }

                // Try to login User
                if (mailTrue && passwordTrue) {
                    LoginActivity.disableLoadingScreen(false, v.getContext());

                    String deviceToken = CompletableFuture.supplyAsync(() -> {
                        try {
                            if(ParameterManager.getDeviceId(v.getContext()) == null) {
                                String token = Pushy.register(getApplicationContext());
                                ParameterManager.setDeviceId(v.getContext(), token);
                            }
                            return ParameterManager.getDeviceId(v.getContext());
                        } catch (PushyException e) {
                            Log.i("PushyException", String.valueOf(e));
                            return "";
                        }
                    }).join();

                    Log.i("Pushy", deviceToken);

                    new UserLogin(insertedEmail, insertedPassword, deviceToken);
                }
            }
        });
    }

    public static void disableLoadingScreen(boolean b, Context context) {
        RelativeLayout loadingScreen = ((Activity) context).findViewById(R.id.loadingScreen);
        EditText email = ((Activity) context).findViewById(R.id.homeScreenEmailInput);
        EditText password = ((Activity) context).findViewById(R.id.homeScreenPasswordInput);
        Button login = ((Activity) context).findViewById(R.id.homeScreenLoginButton);
        TextView register = ((Activity) context).findViewById(R.id.homeScreenRegisterLink);
        TextView forgotPW = ((Activity) context).findViewById(R.id.homeScreenForgotPasswordLink);

        if (!b) {
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
