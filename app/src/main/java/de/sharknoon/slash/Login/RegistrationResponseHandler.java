package de.sharknoon.slash.Login;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import de.sharknoon.slash.R;
import de.sharknoon.slash.Registration.RegistrationResponse;

public class RegistrationResponseHandler {

    private final String SERVER_RESPONSE_STATUS_OK = "OK";
    private final String SERVER_RESPONSE_USER_DOES_NOT_EXIST = "USER_DOES_NOT_EXIST";
    private final String SERVER_RESPONSE_WRONG_PASSWORD = "WRONG_PASSWORD";

    public RegistrationResponseHandler(String serverResponse, Context context) {
        Gson gson = new Gson();
        RegistrationResponse registrationResponse = gson.fromJson(serverResponse, RegistrationResponse.class);

        TextView usernameErrorTextView = ((Activity) context).findViewById(R.id.registerScreenUsernameErrorMessageTextView);
        switch (registrationResponse.getStatus()) {

            case SERVER_RESPONSE_STATUS_OK:
                Log.d("Status", SERVER_RESPONSE_STATUS_OK);
                break;
            case SERVER_RESPONSE_USER_DOES_NOT_EXIST:
                Log.d("Status", SERVER_RESPONSE_USER_DOES_NOT_EXIST);
                break;
            case SERVER_RESPONSE_WRONG_PASSWORD:
                Log.d("Status", SERVER_RESPONSE_WRONG_PASSWORD);
                break;
        }
    }
}
