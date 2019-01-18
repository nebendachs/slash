package de.sharknoon.slash.SharedPreferences;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ParameterManagerTest {
    @Test
    public void setSessionString() {
        Context context = InstrumentationRegistry.getTargetContext();
        ParameterManager.setSession(context, "abc123");
        assertEquals("abc123", ParameterManager.getSession(context));
    }

    @Test
    public void setSessionNull() {
        Context context = InstrumentationRegistry.getTargetContext();
        ParameterManager.setSession(context, "abc123");
        ParameterManager.setSession(context, null);
        assertNull(ParameterManager.getSession(context));
    }
}