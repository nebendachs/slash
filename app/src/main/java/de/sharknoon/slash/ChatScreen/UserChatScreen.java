package de.sharknoon.slash.ChatScreen;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;

import java.util.function.Consumer;

public class UserChatScreen {

    public UserChatScreen(String sessionId, String message, Context context){

        try {
            Gson gson = new Gson();
            ChatScreenMessage homeScreenMessage = new ChatScreenMessage(sessionId, message);
            String jsonHomeScreenMessage = gson.toJson(homeScreenMessage);
            Log.d("JSON", jsonHomeScreenMessage);

            // Open Websocket connection and send session id to server
            createChatScreenClient(context, jsonHomeScreenMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createChatScreenClient(Context context, String jsonHomeScreenMessage){
        Consumer<WebSocketClient> onOpen = webSocketClient -> {
            Log.d("Websocket", "Opened");
            webSocketClient.send(jsonHomeScreenMessage);
        };

        Consumer<String> onMessage = message -> {
            Log.d("Websocket", message);
            message = ""; //TODO: Define Message
            ChatScreenResponseHandler.handleResponse(message, context);
        };

        Consumer<String> onClose = reason -> {
            Log.d("Websocket", "Closed");
        };

        Consumer<Exception> onError = ex -> {
            Log.d("Websocket", String.valueOf(ex));
        };

        String REGISTRATION_URI = "wss://sharknoon.de/slash/chat"; //TODO: Define URI
        new ChatScreenClient(REGISTRATION_URI, context, onOpen, onMessage, onClose, onError);
    }
}
