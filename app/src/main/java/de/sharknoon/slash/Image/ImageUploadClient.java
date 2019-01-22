package de.sharknoon.slash.Image;

import android.content.Context;

import org.java_websocket.client.WebSocketClient;

import java.util.function.Consumer;

import de.sharknoon.slash.SSLWebSocketClient;

class ImageUploadClient extends SSLWebSocketClient {
    public ImageUploadClient(String URL, Context context, Consumer<WebSocketClient> onOpenConsumer, Consumer<String> onMessageConsumer, Consumer<String> onCloseConsumer, Consumer<Exception> onErrorConsumer) {
        super(URL, context, onOpenConsumer, onMessageConsumer, onCloseConsumer, onErrorConsumer);
    }
}
