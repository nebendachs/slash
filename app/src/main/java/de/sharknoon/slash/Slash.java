package de.sharknoon.slash;

import android.app.Application;
import android.content.Context;

public class Slash extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        Slash.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return Slash.context;
    }
}
