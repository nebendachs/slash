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
import me.pushy.sdk.Pushy;

public class UserLogin {
    private static LoginClient loginClient = null;

    public UserLogin(String usernameOrEmail, String password, String deviceToken) {
        try {
            String hashedPassword = UserLogin.hashPassword(password);
            Gson gson = new Gson();
            LoginMessage loginMessage = new LoginMessage(usernameOrEmail, hashedPassword, deviceToken);
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
        };

        Consumer<String> onClose = reason -> {
            Log.d("Websocket", "Closed");
        };

        Consumer<Exception> onError = ex -> {
            Log.d("Websocket", String.valueOf(ex));
        };

        String REGISTRATION_URI = "wss://sharknoon.de/slash/login";
        UserLogin.loginClient = new LoginClient(REGISTRATION_URI, context, onOpen, onMessage, onClose, onError);
    }

    private static String hashPassword(String password) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(password.getBytes());
        return new String(messageDigest.digest());
    }
}
