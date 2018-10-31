package de.sharknoon.slash.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import de.sharknoon.slash.Activties.HomeScreeActivity;
import de.sharknoon.slash.Activties.MainActivity;
import de.sharknoon.slash.Activties.RegisterActivity;
import de.sharknoon.slash.R;
import de.sharknoon.slash.Registration.RegistrationResponse;

public class LoginResponseHandler {

    private static final String SERVER_RESPONSE_STATUS_OK = "OK";
    private static final String SERVER_RESPONSE_USER_DOES_NOT_EXIST = "USER_DOES_NOT_EXIST";
    private static final String SERVER_RESPONSE_WRONG_PASSWORD = "WRONG_PASSWORD";
    private static final String SERVER_RESPONSE_USER_ALREADY_LOGGED_IN = "USER_ALREADY_LOGGED_IN";

    public static void handlerResponse(String serverResponse, Context context) {
        Gson gson = new Gson();
        RegistrationResponse registrationResponse = gson.fromJson(serverResponse, RegistrationResponse.class);

        TextView emailErrorTextView = ((Activity) context).findViewById(R.id.homeScreenWrongEmailTextView);
        TextView passwordErrorTextView = ((Activity) context).findViewById(R.id.homeScreenWrongPasswordTextView);

        switch (registrationResponse.getStatus()) {

            case SERVER_RESPONSE_STATUS_OK:

                ((Activity) context).runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Activity loginActivity = (Activity) context;
                        loginActivity.startActivity(new Intent(context, HomeScreeActivity.class));
                    }
                });

                Log.d("Status", SERVER_RESPONSE_STATUS_OK);
                break;
            case SERVER_RESPONSE_USER_DOES_NOT_EXIST:
                Log.d("Status", SERVER_RESPONSE_USER_DOES_NOT_EXIST);
                ((Activity) context).runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        MainActivity.disableLoadingScreen(true, context);
                        emailErrorTextView.setText(R.string.homeScreenUserDoesNotExist);
                    }
                });
                break;
            case SERVER_RESPONSE_WRONG_PASSWORD:
                Log.d("Status", SERVER_RESPONSE_WRONG_PASSWORD);
                ((Activity) context).runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        MainActivity.disableLoadingScreen(true, context);
                        passwordErrorTextView.setText(R.string.homeScreenWrongPassword);                    }
                });
                break;
            case SERVER_RESPONSE_USER_ALREADY_LOGGED_IN:
                Log.d("Status", SERVER_RESPONSE_USER_ALREADY_LOGGED_IN);
                ((Activity) context).runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        MainActivity.disableLoadingScreen(true, context);
                        emailErrorTextView.setText(R.string.homeScreenUserAlreadyLoggedIn);
                    }
                });
                break;
        }
    }
}
