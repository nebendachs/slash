package de.sharknoon.slash.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import de.sharknoon.slash.ChatMessages.ChatOrProject;

public class ParameterManager {
    private SharedPreferences sharedPreferences;
    private static ChatOrProject currentOpenChatOrProject;

    public ParameterManager() {
        // Blank
    }

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences("de.sharknoon.slash", Context.MODE_PRIVATE);
    }

    public static String getSession(Context context) {
        return getPreferences(context).getString("session", null);
    }

    public static void setSession(Context context, String input) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString("session", input);
        editor.apply();
    }

    public static String getDeviceId(Context context) {
        return getPreferences(context).getString("device", null);
    }

    public static void setDeviceId(Context context, String input) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString("device", input);
        editor.apply();
    }

    public static String getUserId(Context context) {
        return getPreferences(context).getString("user", null);
    }

    public static void setUserId(Context context, String input) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString("user", input);
        editor.apply();
    }

    public static void setCurrentOpenChatOrProject(ChatOrProject currentOpenChatOrProject){
        ParameterManager.currentOpenChatOrProject = currentOpenChatOrProject;
    }

    public static ChatOrProject getCurrentOpenChatOrProject(){
        return currentOpenChatOrProject;
    }
}