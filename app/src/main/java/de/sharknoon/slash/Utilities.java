package de.sharknoon.slash;

import android.util.DisplayMetrics;

public class Utilities {

    public static int convertIntoDP (int size, DisplayMetrics metrics){
        return (size * metrics.densityDpi) / DisplayMetrics.DENSITY_DEFAULT;
    }
}
