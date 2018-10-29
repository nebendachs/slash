package de.sharknoon.slash.Registration;

import android.content.Context;
import android.util.Log;

import org.java_websocket.handshake.ServerHandshake;

import java.util.function.Consumer;

public class UserRegistration {

    private static final int MIN_PASSWORD_LENGTH = 8;

    public UserRegistration(String username, String email, String password, Context context){

        Consumer<ServerHandshake> onOpen = handshakedata -> {
            Log.d("Websocket", "Opened");
        };

        Consumer<String> onMessage = message -> {
            Log.d("Websocket", message);
        };

        Consumer<String> onClose = reason -> {
            Log.d("Websocket", "Closed");
        };

        Consumer<Exception> onError = ex -> {
            Log.d("Websocket", ex.toString());
        };

        String REGISTRATION_URI = "wss://sharknoon.de/slash/login";
        new RegistrationClient(REGISTRATION_URI, context, onOpen, onMessage, onClose, onError);
    }

    public static boolean checkForPasswordGuidelines(String password){

        return password.length() >= UserRegistration.MIN_PASSWORD_LENGTH;
    }
}
