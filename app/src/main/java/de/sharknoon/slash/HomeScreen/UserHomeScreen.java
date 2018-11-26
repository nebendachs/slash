package de.sharknoon.slash.HomeScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;

import java.util.function.Consumer;

import de.sharknoon.slash.Activties.CreateClientProjektActivity;
import de.sharknoon.slash.SharedPreferences.ParameterManager;

public class UserHomeScreen {

    private final String STATUS_GET_HOME = "GET_HOME";
    public static HomeScreenClient homeScreenClient;
    private String sessionId;

    public UserHomeScreen(Context context) {

        try {
            Gson gson = new Gson();
            sessionId = ParameterManager.getSession(context);
            HomeScreenMessage homeScreenMessage = new HomeScreenMessage(sessionId, STATUS_GET_HOME);
            String jsonHomeScreenMessage = gson.toJson(homeScreenMessage);
            Log.d("JSON", jsonHomeScreenMessage);

            // Open Websocket connection and send session id to server
            UserHomeScreen.homeScreenClient = createHomeScreenClient(context, jsonHomeScreenMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Create new Window to create a new Chat or Project
    public void CreateChatOrProject(Context context){
        Activity activity = (Activity) context;
        Intent intent = new Intent(context, CreateClientProjektActivity.class);
        activity.startActivity(intent);
    }

    private HomeScreenClient createHomeScreenClient(Context context, String jsonHomeScreenMessage) {

        Consumer<WebSocketClient> onOpen = webSocketClient -> {
            Log.d("Websocket", "Opened");
            webSocketClient.send(jsonHomeScreenMessage);
        };

        Consumer<String> onMessage = message -> {
            Log.d("Websocket", message);
            HomeScreenResponseHandler.handleResponse(message, context);
        };

        Consumer<String> onClose = reason -> {
            Log.d("Websocket", "Closed");
        };

        Consumer<Exception> onError = ex -> {
            Log.d("Websocket", String.valueOf(ex));
        };

        String REGISTRATION_URI = "wss://sharknoon.de/slash/home";
        return new HomeScreenClient(REGISTRATION_URI, context, onOpen, onMessage, onClose, onError);
    }
}
