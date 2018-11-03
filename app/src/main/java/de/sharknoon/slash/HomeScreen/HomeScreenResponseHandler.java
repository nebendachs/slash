package de.sharknoon.slash.HomeScreen;

import android.app.Activity;
import android.content.Context;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import de.sharknoon.slash.Login.LoginResponse;
import de.sharknoon.slash.R;

public class HomeScreenResponseHandler {

    private static final String SERVER_RESPONSE_STATUS_OK = "OK";

    public static void handleResponse(String serverResponse, Context context){

        Gson gson = new Gson();
        HomeScreenResponse homeScreenResponse = gson.fromJson(serverResponse, HomeScreenResponse.class);

        switch (homeScreenResponse.getStatus()){

            case SERVER_RESPONSE_STATUS_OK:

                Activity homeScreenActivity = (Activity) context;
                LinearLayout parentLayout = (LinearLayout) homeScreenActivity.findViewById(R.id.homeScreenProjectsContainer);
                Contact projects[] = homeScreenResponse.getProjects();

                if(projects.length != 0){
                    for(Contact currentProject : projects){
                        new ContactView(homeScreenActivity,parentLayout, currentProject.getContactImageUrl(), currentProject.getContactName());
                    }
                }
                // TODO Plus icon einfügen
                break;
        }








    }
}
