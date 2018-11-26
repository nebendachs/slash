package de.sharknoon.slash.HomeScreen;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import static de.sharknoon.slash.HomeScreen.UserHomeScreen.homeScreenClient;
import static de.sharknoon.slash.HomeScreen.UserHomeScreen.sessionId;

public class UserCreateClientOrProjekt {

    public void searchSinglePerson(Context context, String username){
        Gson gson = new Gson();
        FindUser user = new FindUser(sessionId,username);
        String jsonChatMessage = gson.toJson(user);
        Log.d("JSON", jsonChatMessage);

        if(homeScreenClient != null){
            homeScreenClient.getWebSocketClient().send(jsonChatMessage);
        }
    }
}
