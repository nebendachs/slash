package de.sharknoon.slash.Registration;

import android.content.Context;

import org.java_websocket.handshake.ServerHandshake;

import java.util.function.Consumer;

import de.sharknoon.slash.SSLWebSocketClient;

public class RegistrationClient extends SSLWebSocketClient {

    public RegistrationClient(String URL, Context context, Consumer<ServerHandshake> onOpenConsumer,
                              Consumer<String> onMessageConsumer, Consumer<String> onCloseConsumer,
                              Consumer<Exception> onErrorConsumer) {
        super(URL, context, onOpenConsumer, onMessageConsumer, onCloseConsumer, onErrorConsumer);
    }
}
