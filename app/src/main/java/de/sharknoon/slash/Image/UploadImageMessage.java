package de.sharknoon.slash.Image;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;

import java.util.function.Consumer;

import de.sharknoon.slash.Login.LoginResponseHandler;

import android.content.Context;


public class UploadImageMessage {
    private static final String BASE_IMAGE_URL = "wss://sharknoon.de/slash/file/";

    private static byte[] imageData;


    public static void setImageData(byte[] imageData) {
        UploadImageMessage.imageData = imageData;
    }

    public static void setImageID(String imageId, Context context) {
        if (imageData != null && imageId.length() > 0) {
            uploadImageWithClient(BASE_IMAGE_URL + imageId, context);
        }
    }

    public static void uploadImageWithClient(String imageUrl, Context context) {
        Consumer<WebSocketClient> onOpen = webSocketClient -> {
            Log.d("Websocket", "Opened");
            Log.d("ImageSize", String.valueOf(imageData.length));
            webSocketClient.send(imageData);
        };

        Consumer<String> onMessage = message -> {
            Log.d("Websocket", message);
        };

        Consumer<String> onClose = reason -> {
            Log.d("Websocket", "Closed");
        };

        Consumer<Exception> onError = ex -> {
            Log.d("Websocket", String.valueOf(ex));
        };
        new ImageUploadClient(imageUrl, context, onOpen, onMessage, onClose, onError);
    }
}
