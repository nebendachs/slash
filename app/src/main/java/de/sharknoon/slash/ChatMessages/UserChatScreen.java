package de.sharknoon.slash.ChatMessages;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import de.sharknoon.slash.Activties.CreateTemplateActivity;
import de.sharknoon.slash.HomeScreen.Chat;
import de.sharknoon.slash.SharedPreferences.ParameterManager;

import static de.sharknoon.slash.HomeScreen.UserHomeScreen.homeScreenClient;

public class UserChatScreen implements Serializable {

    public void setChat(ChatOrProject chatOrProject, Context context, LinearLayout messageScreen){
        fillChatScreen(chatOrProject, context, messageScreen);
        if(messageScreen.getChildAt(messageScreen.getChildCount()-1).hasFocusable()) {
            messageScreen.getChildAt(messageScreen.getChildCount()-1).requestFocus();
        }
    }

    //Fill the Layout with all messages got from server
    public void fillChatScreen(ChatOrProject chatOrProject, Context context, LinearLayout messageScreen){
        if(chatOrProject != null) {
            List<Chat.Message> messageList = chatOrProject.getMessages();
            if(messageList != null) {
                if (messageList.size() > 0) {

                    messageScreen.removeAllViews();

                    Collections.sort(messageList);

                    for (Chat.Message s : messageList) {
                        MessageBuilder builder = new MessageBuilder(context, s, chatOrProject);
                        View view = builder.getView();
                        messageScreen.addView(view);
                    }
                }
            }
        }
    }

    public void startMessageBuilder(Context context, String status, String id){
        Activity templateActivity = (Activity) context;
        Intent intent = new Intent(context, CreateTemplateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("USERCHATSCREEN", this);
        bundle.putString("STATUS", status);
        bundle.putString("ID", id);
        intent.putExtras(bundle);
        templateActivity.startActivity(intent);
    }

    // messagetype: 0 = einfach, 1 = template, 2 = image
    public void sendMessage(int messagetype, Context context, String id, String status, String message, String emotion, String subject){
        switch (messagetype){
            case 0:
                sendSimpleMessage(context, id, status, message);
                break;
            case 1:
                sendTemplateMessage(context, id, status, message, emotion, subject);
                break;
            case 2:
                sendImageMessage(context, id, status, message);
                break;
        }
    }

    private void sendSimpleMessage(Context context, String id, String status, String message){
        SendMessage sendMessage = new SendMessage(ParameterManager.getSession(context),id,"TEXT", message, "", "", status);
        sendJson(sendMessage);
    }

    private void sendTemplateMessage(Context context, String id, String status, String message, String emotion, String subject){
        SendMessage sendMessage = new SendMessage(ParameterManager.getSession(context), id ,"EMOTION", message, subject, emotion, status);
        sendJson(sendMessage);
    }

    private void sendJson(SendMessage message){
        Gson gson = new Gson();
        String jsonChatMessage = gson.toJson(message);
        Log.d("JSON", jsonChatMessage);

        if(homeScreenClient != null){
            homeScreenClient.getWebSocketClient().send(jsonChatMessage);
        }
    }

    private void sendImageMessage(Context context, String id, String status, String message){
        //TODO: Aufruf für Bilder
    }

}
