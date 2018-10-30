package de.sharknoon.slash.Login;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;

import java.security.MessageDigest;
import java.util.function.Consumer;

public class UserLogin {

    public UserLogin(String usernameOrEmail, String password, Context context) {
        try {
            String hashedPassword = UserLogin.hashPassword(password);
            Gson gson = new Gson();
            LoginMessage loginMessage = new LoginMessage(usernameOrEmail, hashedPassword);
            String jsonLoginMessage = gson.toJson(loginMessage);
            Log.d("JSON", jsonLoginMessage);

            Consumer<WebSocketClient> onOpen = webSocketClient -> {
                Log.d("Websocket", "Opened");
                webSocketClient.send(jsonLoginMessage);
            };

            Consumer<String> onMessage = message -> {
                Log.d("Websocket", message);
                new LoginResponseHandler(message, context);
            };

            Consumer<String> onClose = reason -> {
                Log.d("Websocket", "Closed");
            };

            Consumer<Exception> onError = ex -> {
                Log.d("Websocket", ex.toString());
            };

            String LOGIN_URI = "wss://sharknoon.de/slash/login";
            new LoginClient(LOGIN_URI, context, onOpen, onMessage, onClose, onError);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String hashPassword(String password) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(password.getBytes());
        return new String(messageDigest.digest());
    }
}
