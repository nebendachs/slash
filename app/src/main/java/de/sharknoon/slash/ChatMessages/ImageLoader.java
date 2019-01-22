package de.sharknoon.slash.ChatMessages;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.InputStream;
import java.net.URI;
import java.nio.ByteBuffer;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import de.sharknoon.slash.R;
import de.sharknoon.slash.SharedPreferences.ParameterManager;

public class ImageLoader {

    private Context context;
    private final String imageDownloadUrl = "wss://sharknoon.de/slash/file/";

    public ImageLoader(String imageId, Context context, View targetView){
        try {

            this.context = context;
            String imageUrl = imageDownloadUrl + imageId;
            URI serverURI = new URI(imageUrl);
            WebSocketClient webSocketClient = new WebSocketClient(serverURI) {

                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    Log.i("onOpen:","Verbindung aufgebaut");

                    ImageLoaderMessage imageLoaderMessage = new ImageLoaderMessage(ParameterManager.getSession(context));
                    String message = new Gson().toJson(imageLoaderMessage);
                    this.send(message);
                }

                @Override
                public void onMessage(String message) {
                    Log.i("onMessage:",message);

                }

                @Override
                public void onMessage(ByteBuffer imageByteBuffer) {

                    new Handler(Looper.getMainLooper()).post(() -> {
                        byte[] imageBytes= new byte[imageByteBuffer.remaining()];
                        imageByteBuffer.get(imageBytes);

                        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

                        if(targetView instanceof ImageView)
                            ((ImageView)targetView).setImageBitmap(bitmap);
                        else if(targetView instanceof TextView) {
                            Drawable d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, targetView.getWidth(), targetView.getWidth(), false));
                            ((TextView) targetView).setCompoundDrawablesWithIntrinsicBounds(null, d, null, null);
                        }
                    });
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.i("onClose:",reason);
                }

                @Override
                public void onError(Exception ex) {
                    ex.printStackTrace();
                }
            };


            SSLSocketFactory sslSocketFactory = this.getSSLClient();
            webSocketClient.setSocket(sslSocketFactory.createSocket());
            webSocketClient.getSocket().setReceiveBufferSize(Integer.MAX_VALUE);
            webSocketClient.getSocket().setSendBufferSize(Integer.MAX_VALUE);
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
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagerFactory.getKeyManagers(), tmf.getTrustManagers(), null);
        return sslContext.getSocketFactory();
    }
}
