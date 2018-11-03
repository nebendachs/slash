package de.sharknoon.slash.HomeScreen;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;

import java.util.function.Consumer;

public class UserHomeScreen {

    public UserHomeScreen(String sessionId, Context context) {

        try {
            Gson gson = new Gson();
            HomeScreenMessage homeScreenMessage = new HomeScreenMessage(sessionId);
            String jsonHomeScreenMessage = gson.toJson(homeScreenMessage);
            Log.d("JSON", jsonHomeScreenMessage);

            // Open Websocket connection and send session id to server
            createHomeScreenClient(context, jsonHomeScreenMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createHomeScreenClient(Context context, String jsonHomeScreenMessage) {

        Consumer<WebSocketClient> onOpen = webSocketClient -> {
            Log.d("Websocket", "Opened");
            webSocketClient.send(jsonHomeScreenMessage);
        };

        Consumer<String> onMessage = message -> {
            Log.d("Websocket", message);
            message = "{'projects' : [{'contactname': 'aume', 'contactimageurl': './home'}, {'contactname': 'aume2', 'contactimageurl': './home'}, {'contactname': 'aume3', 'contactimageurl': './home'}], 'contacts': [{'contactname': 'pa.kempf', 'contactimageurl': './home'}, {'contactname': 'pa.kempf2', 'contactimageurl': './home'}, {'contactname': 'pa.kempf3', 'contactimageurl': './home'}, {'contactname': 'pa.kempf4', 'contactimageurl': './home'}, {'contactname': 'pa.kempf5', 'contactimageurl': './home'}], 'status':'OK','message':'Successfully logged in','sessionid':'fbac477c-0060-4623-9f0f-aae373611aab'}";
            HomeScreenResponseHandler.handleResponse(message, context);
        };

        Consumer<String> onClose = reason -> {
            Log.d("Websocket", "Closed");
        };

        Consumer<Exception> onError = ex -> {
            Log.d("Websocket", String.valueOf(ex));
        };

        String REGISTRATION_URI = "wss://sharknoon.de/slash/home";
        new HomeScreenClient(REGISTRATION_URI, context, onOpen, onMessage, onClose, onError);
    }
}
