package de.sharknoon.slash.Login;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import de.sharknoon.slash.Activties.MainActivity;
import de.sharknoon.slash.R;
import de.sharknoon.slash.Registration.RegistrationResponse;

public class LoginResponseHandler {

    private static final String SERVER_RESPONSE_STATUS_OK = "OK";
    private static final String SERVER_RESPONSE_USER_DOES_NOT_EXIST = "USER_DOES_NOT_EXIST";
    private static final String SERVER_RESPONSE_WRONG_PASSWORD = "WRONG_PASSWORD";

    public static void handlerResponse(String serverResponse, Context context) {
        Gson gson = new Gson();
        RegistrationResponse registrationResponse = gson.fromJson(serverResponse, RegistrationResponse.class);

        TextView emailErrorTextView = ((Activity) context).findViewById(R.id.homeScreenWrongEmailTextView);
        TextView passwordErrorTextView = ((Activity) context).findViewById(R.id.homeScreenWrongPasswordTextView);

        switch (registrationResponse.getStatus()) {

            case SERVER_RESPONSE_STATUS_OK:
                Log.d("Status", SERVER_RESPONSE_STATUS_OK);
                MainActivity.disableLoadingScreen(true, context);
                break;
            case SERVER_RESPONSE_USER_DOES_NOT_EXIST:
                Log.d("Status", SERVER_RESPONSE_USER_DOES_NOT_EXIST);
                emailErrorTextView.setText(R.string.homeScreenUserDoesNotExist);
                break;
            case SERVER_RESPONSE_WRONG_PASSWORD:
                Log.d("Status", SERVER_RESPONSE_WRONG_PASSWORD);
                passwordErrorTextView.setText(R.string.homeScreenWrongPassword);
                break;
        }
    }
}
