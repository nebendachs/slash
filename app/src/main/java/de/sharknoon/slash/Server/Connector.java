package de.sharknoon.slash.Server;

import  android.util.Log;

import com.neovisionaries.ws.client.*;
import java.io.IOException;

public class Connector {

    private WebSocket ws = null;

    public Connector(){
        WebSocketFactory factory = new WebSocketFactory();

        try{
            ws = factory.createSocket("https://sharknoon.de/slash");
            ws.addListener(new WebSocketAdapter() {
                @Override
                public void onTextMessage(WebSocket websocket, String message) throws Exception {
                    Log.i("Websocket", "onTextMessage: " + message);
                }
            });

            ws.connectAsynchronously();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void sendMessage(){
        if(ws.isOpen()){
            ws.sendText("Message from Android!");
        }
    }
}