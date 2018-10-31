package de.sharknoon.slash.Login;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;

import java.security.MessageDigest;
import java.util.function.Consumer;

import de.sharknoon.slash.R;

public class UserLogin {
    private static LoginClient loginClient = null;

    public UserLogin(String usernameOrEmail, String password) {
        try {
            String hashedPassword = UserLogin.hashPassword(password);
            Gson gson = new Gson();
            LoginMessage loginMessage = new LoginMessage(usernameOrEmail, hashedPassword);
            String jsonLoginMessage = gson.toJson(loginMessage);
            Log.d("JSON", jsonLoginMessage);

            if(UserLogin.loginClient != null){
                UserLogin.loginClient.getWebSocketClient().send(jsonLoginMessage);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createLoginClient(Context context){
        Consumer<WebSocketClient> onOpen = webSocketClient -> {
            Log.d("Websocket", "Opened");
        };

        Consumer<String> onMessage = message -> {
            Log.d("Websocket", message);
            LoginResponseHandler.handlerResponse(message, context);
            disableLoadingScreen(true, context);
        };

        Consumer<String> onClose = reason -> {
            Log.d("Websocket", "Closed");
        };

        Consumer<Exception> onError = ex -> {
            Log.d("Websocket", String.valueOf(ex));
            disableLoadingScreen(true, context);
        };

        String REGISTRATION_URI = "wss://sharknoon.de/slash/register";
        UserLogin.loginClient = new LoginClient(REGISTRATION_URI, context, onOpen, onMessage, onClose, onError);
    }

    private static String hashPassword(String password) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(password.getBytes());
        return new String(messageDigest.digest());
    }


    public static void disableLoadingScreen(boolean b, Context context){
        RelativeLayout loadingScreen = ((Activity) context).findViewById(R.id.loadingscreen);
        EditText email = ((Activity) context).findViewById(R.id.homeScreenEmailInput);
        EditText password = ((Activity) context).findViewById(R.id.homeScreenPasswordInput);
        Button login = ((Activity) context).findViewById(R.id.homeScreenLoginButton);
        TextView register = ((Activity) context).findViewById(R.id.homeScreenRegisterLink);
        TextView forgotPW = ((Activity) context).findViewById(R.id.homeScreenForgotPasswordLink);

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
