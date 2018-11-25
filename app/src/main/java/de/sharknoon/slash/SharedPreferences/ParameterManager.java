package de.sharknoon.slash.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class ParameterManager {
    private SharedPreferences sharedPreferences;

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
        editor.commit();
    }

    public static String getDeviceId(Context context) {
        return getPreferences(context).getString("device", null);
    }

    public static void setDeviceId(Context context, String input) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString("device", input);
        editor.commit();
    }
}