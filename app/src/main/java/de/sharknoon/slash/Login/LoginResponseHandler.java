package de.sharknoon.slash.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;

import com.google.gson.Gson;

import de.sharknoon.slash.Activties.HomeScreenActivity;
import de.sharknoon.slash.Activties.LoginActivity;
import de.sharknoon.slash.R;
import de.sharknoon.slash.SharedPreferences.ParameterManager;

class LoginResponseHandler {

    private static final String SERVER_RESPONSE_STATUS_OK = "OK";
    private static final String SERVER_RESPONSE_USER_DOES_NOT_EXIST = "USER_DOES_NOT_EXIST";
    private static final String SERVER_RESPONSE_WRONG_PASSWORD = "WRONG_PASSWORD";
    private static final String SERVER_RESPONSE_USER_ALREADY_LOGGED_IN = "USER_ALREADY_LOGGED_IN";

    public static void handlerResponse(String serverResponse, Context context) {
        Gson gson = new Gson();
        LoginResponse loginResponse = gson.fromJson(serverResponse, LoginResponse.class);

        EditText editTextEmail = ((Activity) context).findViewById(R.id.homeScreenEmailInput);
        EditText editTextPassword = ((Activity) context).findViewById(R.id.homeScreenPasswordInput);

        switch (loginResponse.getStatus()) {

            case SERVER_RESPONSE_STATUS_OK:

                ((Activity) context).runOnUiThread(() -> {

                    // Save session id in shared parameters and go to HomeScreen Activity
                    ParameterManager.setSession(context, loginResponse.getSessionid());
                    ParameterManager.setUserId(context, loginResponse.getUserID());
                    Activity loginActivity = (Activity) context;
                    Intent intent = new Intent(context, HomeScreenActivity.class);
                    loginActivity.startActivity(intent);
                });

                Log.d("Status", SERVER_RESPONSE_STATUS_OK);
                break;
            case SERVER_RESPONSE_USER_DOES_NOT_EXIST:
                Log.d("Status", SERVER_RESPONSE_USER_DOES_NOT_EXIST);
                ((Activity) context).runOnUiThread(() -> {
                    LoginActivity.disableLoadingScreen(true, context);
                    editTextEmail.setError(context.getResources().getString(R.string.homeScreenUserDoesNotExist));
                });
                break;
            case SERVER_RESPONSE_WRONG_PASSWORD:
                Log.d("Status", SERVER_RESPONSE_WRONG_PASSWORD);
                ((Activity) context).runOnUiThread(() -> {
                    LoginActivity.disableLoadingScreen(true, context);
                    editTextPassword.setError(context.getResources().getString(R.string.homeScreenWrongPassword));
                });
                break;
            case SERVER_RESPONSE_USER_ALREADY_LOGGED_IN:
                Log.d("Status", SERVER_RESPONSE_USER_ALREADY_LOGGED_IN);
                ((Activity) context).runOnUiThread(() -> {
                    LoginActivity.disableLoadingScreen(true, context);
                    editTextEmail.setError(context.getResources().getString(R.string.homeScreenUserAlreadyLoggedIn));
                });
                break;
        }
    }
}
