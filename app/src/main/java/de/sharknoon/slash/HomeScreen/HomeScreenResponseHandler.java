package de.sharknoon.slash.HomeScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import de.sharknoon.slash.Activties.ChatScreenActivity;
import de.sharknoon.slash.Activties.HomeScreenActivity;
import de.sharknoon.slash.ChatMessages.ChatOrProject;
import de.sharknoon.slash.R;

public class HomeScreenResponseHandler {

    private static final String GET_HOME_OK_STATUS = "OK_HOME";
    private static final String JSON_FIELD_STATUS = "status";
    private static final String GET_USER_OK_STATUS = "OK_USER";
    private static final String ADD_MESSAGE_OK_STATUS = "OK_CHAT";

    public static void handleResponse(String serverResponse, Context context) {

        // No open projects or chats
        if (serverResponse.isEmpty()) {
            return;
        }

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(serverResponse).getAsJsonObject();
        String status = jsonObject.get(JSON_FIELD_STATUS).getAsString();

        Activity homeScreenActivity = (Activity) context;

        Gson gson = new Gson();

        switch (status) {

            case GET_HOME_OK_STATUS:

                HomeScreenResponse homeScreenResponse = gson.fromJson(serverResponse, HomeScreenResponse.class);

                LinearLayout parentLayoutProjects = homeScreenActivity.findViewById(R.id.homeScreenProjectsContainer);
                FlexboxLayout parentLayoutContacts = homeScreenActivity.findViewById(R.id.homeScreenContactsContainer);

                parentLayoutContacts.removeAllViewsInLayout();
                parentLayoutProjects.removeAllViewsInLayout();

                Project projects[] = homeScreenResponse.getProjects();

                if (projects.length != 0) {
                    for (Project currentProject : projects) {
                        new ContactView(homeScreenActivity, parentLayoutProjects, currentProject.getName(), currentProject);
                    }
                }

                Chat chats[] = homeScreenResponse.getChats();

                if (chats.length != 0) {
                    for (Chat currentContact : chats) {
                        new ContactView(homeScreenActivity, parentLayoutContacts,
                                currentContact.getPartnerUsername(), currentContact);
                    }
                }
                break;

            case GET_USER_OK_STATUS:
                SearchedUsers response = gson.fromJson(serverResponse, SearchedUsers.class);

                ((Activity) context).runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        //Show a list
                    }
                });

                break;

            case ADD_MESSAGE_OK_STATUS:
                JsonParser parser = new JsonParser();
                JsonObject object = parser.parse(serverResponse).getAsJsonObject();
                JsonObject chat1 = object.getAsJsonObject("chat");

                Gson chatGson = new Gson();
                Chat chat = chatGson.fromJson(chat1, Chat.class);
                ChatOrProject chatOrProject = new ChatOrProject(chat, null);

                ((Activity) context).runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        ChatScreenActivity.setChat(chatOrProject, context);
                    }
                });
                break;
        }
    }
}
