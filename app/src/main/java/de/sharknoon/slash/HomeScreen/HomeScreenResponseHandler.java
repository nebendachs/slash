package de.sharknoon.slash.HomeScreen;

import android.app.Activity;
import android.content.Context;
import android.widget.LinearLayout;

import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import de.sharknoon.slash.R;

public class HomeScreenResponseHandler {

    private static final String GET_HOME_OK_STATUS = "OK_HOME";
    private static final String JSON_FIELD_STATUS = "status";

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
                                currentContact.getNameB(), currentContact.getPersonB());
                    }
                }
                break;
        }
    }
}
