package de.sharknoon.slash.HomeScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.LinearLayout;

import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import de.sharknoon.slash.Activties.ChatScreenActivity;
import de.sharknoon.slash.Activties.HomeScreenActivity;
import de.sharknoon.slash.Fragments.PeopleSelector;
import de.sharknoon.slash.ChatMessages.ChatOrProject;
import de.sharknoon.slash.R;

public class HomeScreenResponseHandler {

    private static final String GET_HOME_OK_STATUS = "OK_HOME";
    private static final String JSON_FIELD_STATUS = "status";
    private static final String OK_USERS = "OK_USERS";
    private static final String JSON_FIELD_CHAT = "chat";
    private static final String JSON_FIELD_PROJECT = "project";
    private static final String CHAT_OK_STATUS = "OK_CHAT";
    private static final String PROJECT_OK_STATUS = "OK_PROJECT";

    public static void handleResponse(String serverResponse, Context context) {

        // No open projects or chats
        if (serverResponse.isEmpty()) {
            return;
        }

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(serverResponse).getAsJsonObject();
        String status = jsonObject.get(JSON_FIELD_STATUS).getAsString();

        Activity homeScreenActivity = (Activity) context;
        ChatOrProject chatOrProject = new ChatOrProject(null, null);

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

            case CHAT_OK_STATUS:
                JsonParser chatParser = new JsonParser();
                JsonObject chatObject = chatParser.parse(serverResponse).getAsJsonObject();
                JsonObject chatMessage = chatObject.getAsJsonObject(JSON_FIELD_CHAT);
                Chat chat = gson.fromJson(chatMessage, Chat.class);
                chatOrProject.setChat(chat);

                ((Activity) context).runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        ChatScreenActivity.setChat(chatOrProject, context);
                    }
                });
                break;

            case PROJECT_OK_STATUS:
                JsonParser projectParser = new JsonParser();
                JsonObject projectObject = projectParser.parse(serverResponse).getAsJsonObject();
                JsonObject projectMessage = projectObject.getAsJsonObject(JSON_FIELD_PROJECT);
                Project project = gson.fromJson(projectMessage, Project.class);
                chatOrProject.setProject(project);

                ((Activity) context).runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        ChatScreenActivity.setChat(chatOrProject, context);
                    }
                });
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
