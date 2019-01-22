package de.sharknoon.slash.Registration;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import de.sharknoon.slash.R;

class RegistrationResponseHandler {

    private static final String SERVER_RESPONSE_STATUS_OK = "OK";
    private static final String SERVER_RESPONSE_STATUS_WRONG_USERNAME = "WRONG_USERNAME";
    private static final String SERVER_RESPONSE_STATUS_WRONG_EMAIL = "WRONG_EMAIL";
    private static final String SERVER_RESPONSE_STATUS_USERNAME_ALREADY_REGISTERED = "USERNAME_ALREADY_REGISTERED";
    private static final String SERVER_RESPONSE_STATUS_EMAIL_ALREADY_REGISTERED = "EMAIL_ALREADY_REGISTERED";

    public static void handleResponse(String serverResponse, Context context) {
        Gson gson = new Gson();
        RegistrationResponse registrationResponse = gson.fromJson(serverResponse, RegistrationResponse.class);

        TextView usernameErrorTextView = ((Activity) context).findViewById(R.id.registerScreenUsernameErrorMessageTextView);
        switch (registrationResponse.getStatus()) {

            case SERVER_RESPONSE_STATUS_OK:
                Log.d("Status", SERVER_RESPONSE_STATUS_OK);
                Activity registrationActivity = (Activity) context;
                registrationActivity.finish();
                RegistrationResponseHandler.showSuccessToast(context);
                break;

            case SERVER_RESPONSE_STATUS_WRONG_USERNAME:
                Log.d("Status", SERVER_RESPONSE_STATUS_WRONG_USERNAME);
                usernameErrorTextView.setText(context.getText(R.string.registerScreenEmptyUsernameMessage));
                break;

            case SERVER_RESPONSE_STATUS_WRONG_EMAIL:
                Log.d("Status", SERVER_RESPONSE_STATUS_WRONG_EMAIL);
                usernameErrorTextView.setText(context.getText(R.string.registerScreenEmptyUsernameMessage));
                break;

            case SERVER_RESPONSE_STATUS_USERNAME_ALREADY_REGISTERED:
                Log.d("Status", SERVER_RESPONSE_STATUS_USERNAME_ALREADY_REGISTERED);
                usernameErrorTextView.setText(context.getText(R.string.registerScreenUsernameAlreadyRegisteredMessage));
                break;

            case SERVER_RESPONSE_STATUS_EMAIL_ALREADY_REGISTERED:
                Log.d("Status", SERVER_RESPONSE_STATUS_EMAIL_ALREADY_REGISTERED);
                usernameErrorTextView.setText(context.getText(R.string.registerScreenEmailAlreadyRegisteredMessage));
                usernameErrorTextView.setText(registrationResponse.getMessage());
                break;
        }
    }

    private static void showSuccessToast(Context activityContext){
        CharSequence message = activityContext.getString(R.string.registerScreenSuccessMessage);
        Activity registrationActivity = (Activity) activityContext;
        registrationActivity.runOnUiThread(() -> Toast.makeText(registrationActivity, message, Toast.LENGTH_LONG).show());
    }
}
