package de.sharknoon.slash.SharedPreferences;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ParameterManagerTest {
    private Context context = ApplicationProvider.getApplicationContext();

    @Test
    public void setSession() {
        ParameterManager.setSession(context, "abc123");
        assertThat(ParameterManager.getSession(context)).isEqualTo("abc123");
    }
}