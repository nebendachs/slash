package de.sharknoon.slash;

import android.content.Context;
import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.InputStream;
import java.net.URI;
import java.security.KeyStore;
import java.util.function.Consumer;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public abstract class SSLWebSocketClient {

    private Context context;

    public SSLWebSocketClient(String URL, Context context, Consumer<ServerHandshake> onOpenConsumer,
                              Consumer<String> onMessageConsumer, Consumer<String> onCloseConsumer,
                              Consumer<Exception> onErrorConsumer) {
        this.context = context;

        try {
            URI serverURI = new URI(URL);
            WebSocketClient webSocketClient = new WebSocketClient(serverURI) {

                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    onOpenConsumer.accept(handshakedata);
                }

                @Override
                public void onMessage(String message) {
                    onMessageConsumer.accept(message);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    onCloseConsumer.accept(reason);
                }

                @Override
                public void onError(Exception ex) {
                    onErrorConsumer.accept(ex);
                }
            };

            SSLSocketFactory sslSocketFactory = this.getSSLClient();
            webSocketClient.setSocket(sslSocketFactory.createSocket());
            webSocketClient.connect();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private SSLSocketFactory getSSLClient() throws Exception {

        // Load keystore from resources
        InputStream keystoreInputStream = this.context.getResources().openRawResource(R.raw.slash);
        KeyStore keystore = KeyStore.getInstance("BKS");
        String KEYSTORE_PASSWORD = "AUMEWS";
        keystore.load(keystoreInputStream, KEYSTORE_PASSWORD.toCharArray());

        // Create TrustManager Factory
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("X509");
        keyManagerFactory.init(keystore, KEYSTORE_PASSWORD.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509");
        tmf.init(keystore);

        // Create SSLSocketFactory
        // SSLContext sslContext = null;
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagerFactory.getKeyManagers(), tmf.getTrustManagers(), null);
        return sslContext.getSocketFactory();
    }
}
