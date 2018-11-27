package de.sharknoon.slash.ChatMessages;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import de.sharknoon.slash.HomeScreen.FindUser;
import de.sharknoon.slash.SharedPreferences.ParameterManager;

import static de.sharknoon.slash.HomeScreen.UserHomeScreen.homeScreenClient;

public class UserCreateClientOrProjekt {

    public void searchSinglePerson(Context context, String username){
        Gson gson = new Gson();
        FindUser user = new FindUser(ParameterManager.getSession(context),username);
        String jsonChatMessage = gson.toJson(user);
        Log.d("JSON", jsonChatMessage);

        if(homeScreenClient != null){
            homeScreenClient.getWebSocketClient().send(jsonChatMessage);
        }
    }
}
