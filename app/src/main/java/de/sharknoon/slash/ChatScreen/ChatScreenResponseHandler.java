package de.sharknoon.slash.ChatScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;

import de.sharknoon.slash.Activties.ChatScreenActivity;
import de.sharknoon.slash.Activties.HomeScreenActivity;
import de.sharknoon.slash.R;

public class ChatScreenResponseHandler {

    private static final String SERVER_RESPONSE_STATUS_OK = "OK";
    private static final String SERVER_RESPONSE_CHAT_MESSAGES = "ChatMessages";

    public static void handleResponse(String serverResponse, Context context) {

        Gson gson = new Gson();
        ChatScreenResponse chatScreenResponse = gson.fromJson(serverResponse, ChatScreenResponse.class);

        switch (chatScreenResponse.getStatus()) {

            case SERVER_RESPONSE_STATUS_OK:

                Activity chatScreen = (Activity) context;

                ((Activity) context).runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        //TODO: Resolve messages answer
                        String[] messages = {};
                        ChatScreenActivity.fillChatScreen(messages, context);
                    }
                });
                break;
        }
    }
}
