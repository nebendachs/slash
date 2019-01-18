package de.sharknoon.slash.Login;

import android.content.Context;

import org.java_websocket.client.WebSocketClient;

import java.util.function.Consumer;

import de.sharknoon.slash.SSLWebSocketClient;

class LoginClient extends SSLWebSocketClient {

    public LoginClient(String URL, Context context, Consumer<WebSocketClient> onOpenConsumer,
                              Consumer<String> onMessageConsumer, Consumer<String> onCloseConsumer,
                              Consumer<Exception> onErrorConsumer) {
        super(URL, context, onOpenConsumer, onMessageConsumer, onCloseConsumer, onErrorConsumer);
    }
}
