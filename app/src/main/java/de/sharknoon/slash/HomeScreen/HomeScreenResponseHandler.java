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
import de.sharknoon.slash.Fragments.PeopleSelector;
import de.sharknoon.slash.R;

public class HomeScreenResponseHandler {

    private static final String GET_HOME_OK_STATUS = "OK_HOME";
    private static final String JSON_FIELD_STATUS = "status";
    private static final String GET_USER_OK_STATUS = "OK_USER";
    private static final String ADD_MESSAGE_OK_STATUS = "OK_CHAT";
    private static final String NO_USER_FOUND_STATUS = "NO_USER_FOUND";
    private static final String OK_USERS = "OK_USERS";
    private static final String OK_PROJEKT = "OK_PROJEKT";

    public static void handleResponse(String serverResponse, Context context) {

        // No open projects or chats
        if (serverResponse.isEmpty()) {
            return;
        }

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(serverResponse).getAsJsonObject();
        String status = jsonObject.get(JSON_FIELD_STATUS).getAsString();

        Activity homeScreenActivity = (Activity) context;

        switch (status) {

            case GET_HOME_OK_STATUS:

                Gson gson = new Gson();
                HomeScreenResponse homeScreenResponse = gson.fromJson(serverResponse, HomeScreenResponse.class);
                // Handle projects
                LinearLayout parentLayoutProjects = homeScreenActivity.findViewById(R.id.homeScreenProjectsContainer);
                Project projects[] = homeScreenResponse.getProjects();

                if (projects.length != 0) {
                    for (Project currentProject : projects) {
                        new ContactView(homeScreenActivity, parentLayoutProjects, currentProject.getImage(),
                                currentProject.getName(), currentProject.getId());
                    }
                }

                // Handle contacts
                FlexboxLayout parentLayoutContacts = homeScreenActivity.findViewById(R.id.homeScreenContactsContainer);
                Chat chats[] = homeScreenResponse.getChats();

                if (chats.length != 0) {
                    for (Chat currentContact : chats) {
                        new ContactView(homeScreenActivity, parentLayoutContacts, "",
                                currentContact.getPartnerUsername(), currentContact.getPersonB(),
                                currentContact.getMessages(), currentContact.getId());
                    }
                }
                break;

            case GET_USER_OK_STATUS:

                ((Activity) context).runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        // Open a new ChatScreenActivity
                        Activity loginActivity = (Activity) context;
                        Intent intent = new Intent(context, ChatScreenActivity.class);
                        loginActivity.startActivity(intent);
                    }
                });

                break;

            case ADD_MESSAGE_OK_STATUS:

                JsonParser parser = new JsonParser();
                JsonObject object = parser.parse(serverResponse).getAsJsonObject();
                JsonObject chat1 = object.getAsJsonObject("chat");

                Gson chatGson = new Gson();
                Chat chat = chatGson.fromJson(chat1, Chat.class);
                ChatScreenActivity.fillChatScreen(chat.getMessages());
                break;

            case OK_USERS:

                Gson personSearchGson = new Gson();
                PersonSearchResult personSearchResult = personSearchGson.fromJson(serverResponse, PersonSearchResult.class);

                Intent setPersonSearchResultIntent = new Intent(PeopleSelector.PeopleSearchResultReceiver.ACTION);
                setPersonSearchResultIntent.putExtra(PeopleSelector.PeopleSearchResultReceiver.ACTION, personSearchResult);
                context.sendBroadcast(setPersonSearchResultIntent);
                break;
        }
    }
}
