package de.sharknoon.slash.Registration;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import de.sharknoon.slash.R;

public class RegistrationResponseHandler {

    private static final String SERVER_RESPONSE_STATUS_OK = "OK";
    private static final String SERVER_RESPONSE_STATUS_WRONG_USERNAME = "WRONG_USERNAME";
    private static final String SERVER_RESPONSE_STATUS_WRONG_EMAIL = "WRONG_EMAIL";
    private static final String SERVER_RESPONSE_STATUS_USERNAME_ALREADY_REGISTERED = "USERNAME_ALREADY_REGISTERED";
    private static final String SERVER_RESPONSE_STATUS_EMAIL_ALREADY_REGISTERED = "EMAIL_ALREADY_REGISTERED";

    public static void handlerResponse(String serverResponse, Context context) {
        Gson gson = new Gson();
        RegistrationResponse registrationResponse = gson.fromJson(serverResponse, RegistrationResponse.class);

        TextView usernameErrorTextView = ((Activity) context).findViewById(R.id.registerScreenUsernameErrorMessageTextView);
        switch (registrationResponse.getStatus()) {

            case SERVER_RESPONSE_STATUS_OK:
                Log.d("Status", SERVER_RESPONSE_STATUS_OK);
    //            Activity registrationActivity = (Activity) context;
    //            registrationActivity.finish();
                break;

            case SERVER_RESPONSE_STATUS_WRONG_USERNAME:
                Log.d("Status", SERVER_RESPONSE_STATUS_WRONG_USERNAME);
                usernameErrorTextView.setText(registrationResponse.getMessage());
                break;

            case SERVER_RESPONSE_STATUS_WRONG_EMAIL:
                Log.d("Status", SERVER_RESPONSE_STATUS_WRONG_EMAIL);
                usernameErrorTextView.setText(registrationResponse.getMessage());
                break;

            case SERVER_RESPONSE_STATUS_USERNAME_ALREADY_REGISTERED:
                Log.d("Status", SERVER_RESPONSE_STATUS_USERNAME_ALREADY_REGISTERED);
                usernameErrorTextView.setText(registrationResponse.getMessage());
                break;

            case SERVER_RESPONSE_STATUS_EMAIL_ALREADY_REGISTERED:
                Log.d("Status", SERVER_RESPONSE_STATUS_EMAIL_ALREADY_REGISTERED);
                usernameErrorTextView.setText(registrationResponse.getMessage());
                break;
        }
    }
}
