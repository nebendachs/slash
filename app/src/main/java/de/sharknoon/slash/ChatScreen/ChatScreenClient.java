package de.sharknoon.slash.ChatScreen;

import android.content.Context;
import org.java_websocket.client.WebSocketClient;
import java.util.function.Consumer;
import de.sharknoon.slash.SSLWebSocketClient;

public class ChatScreenClient extends SSLWebSocketClient {

    public ChatScreenClient(String URL, Context context, Consumer<WebSocketClient> onOpenConsumer,
                            Consumer<String> onMessageConsumer, Consumer<String> onCloseConsumer,
                            Consumer<Exception> onErrorConsumer) {
        super(URL, context, onOpenConsumer, onMessageConsumer, onCloseConsumer, onErrorConsumer);
    }
}
