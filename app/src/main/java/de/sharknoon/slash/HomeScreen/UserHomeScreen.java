package de.sharknoon.slash.HomeScreen;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;

import java.util.function.Consumer;

public class UserHomeScreen {

    private final String STATUS_GET_HOME = "GET_HOME";
    public static HomeScreenClient homeScreenClient;
    public static String sessionId;

    public UserHomeScreen(String sessionId, Context context) {

        try {
            UserHomeScreen.sessionId = sessionId;
            Gson gson = new Gson();
            HomeScreenMessage homeScreenMessage = new HomeScreenMessage(sessionId, STATUS_GET_HOME);
            String jsonHomeScreenMessage = gson.toJson(homeScreenMessage);
            Log.d("JSON", jsonHomeScreenMessage);

            // Open Websocket connection and send session id to server
            UserHomeScreen.homeScreenClient = createHomeScreenClient(context, jsonHomeScreenMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void FindUser(String username){
        Gson gson = new Gson();
        FindUser user = new FindUser(sessionId,username);
        String jsonChatMessage = gson.toJson(user);
        Log.d("JSON", jsonChatMessage);

        if(homeScreenClient != null){
            homeScreenClient.getWebSocketClient().send(jsonChatMessage);
        }
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
