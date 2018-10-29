package de.sharknoon.slash.Registration;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;

import java.security.MessageDigest;
import java.util.function.Consumer;

public class UserRegistration {

    private static final int MIN_PASSWORD_LENGTH = 8;

    public UserRegistration(String username, String email, String password, Context context) {

        try {
            String hashedPassword = UserRegistration.hashPassword(password);
            Gson gson = new Gson();
            RegistrationMessage registrationMessage = new RegistrationMessage(username, email, hashedPassword);
            String jsonRegistrationMessage = gson.toJson(registrationMessage);
            Log.d("JSON", jsonRegistrationMessage);

            Consumer<WebSocketClient> onOpen = webSocketClient -> {
                Log.d("Websocket", "Opened");
                webSocketClient.send(jsonRegistrationMessage);
            };

            Consumer<String> onMessage = message -> {
                Log.d("Websocket", message);
                new RegistrationResponseHandler(message, context);
            };

            Consumer<String> onClose = reason -> {
                Log.d("Websocket", "Closed");
            };

            Consumer<Exception> onError = ex -> {
                Log.d("Websocket", ex.toString());
            };

            String REGISTRATION_URI = "wss://sharknoon.de/slash/login";
            new RegistrationClient(REGISTRATION_URI, context, onOpen, onMessage, onClose, onError);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean checkForPasswordGuidelines(String password) {
        return password.length() >= UserRegistration.MIN_PASSWORD_LENGTH;
    }

    public static String hashPassword(String password) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(password.getBytes());
        return new String(messageDigest.digest());
    }
}
