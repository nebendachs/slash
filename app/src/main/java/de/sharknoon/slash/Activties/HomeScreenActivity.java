package de.sharknoon.slash.Activties;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import de.sharknoon.slash.Fragments.Home;
import de.sharknoon.slash.Fragments.Profile;
import de.sharknoon.slash.HomeScreen.UserHomeScreen;
import de.sharknoon.slash.R;
import de.sharknoon.slash.SharedPreferences.ParameterManager;

public class HomeScreenActivity extends AppCompatActivity {
    private UserHomeScreen screen;
    private FragmentManager fragmentManager;
    private Fragment home;
    private Fragment profile;
    private ErrorToastReceiver errorToastReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ParameterManager.setHomeScreenActivtiy(this);

        screen = new UserHomeScreen(this);

        fragmentManager = getSupportFragmentManager();

        home = Home.newInstance();
        profile = Profile.newInstance();

        FragmentTransaction fragmentTransaction;
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, home).commit();

        errorToastReceiver = new ErrorToastReceiver();
        IntentFilter intentFilter = new IntentFilter(ErrorToastReceiver.ACTION);
        registerReceiver(errorToastReceiver, intentFilter);

        this.handleBottomNavigation();
        this.handlePullRefresh();
    }

    private void handleBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            FragmentTransaction fragmentTransaction;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment, home).commit();
                    screen.askForProjectsChats();
                    return true;
                case R.id.navigation_profile:
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment, profile).commit();
                    return true;
            }
            return false;
        });
    }

    private void handlePullRefresh() {
        SwipeRefreshLayout swipe = findViewById(R.id.coordinatorLayout);
        swipe.setOnRefreshListener(() -> {
            screen.askForProjectsChats();
            swipe.setRefreshing(false);
        });
    }

    public class ErrorToastReceiver extends BroadcastReceiver {
        public static final String ACTION = "de.sharknoon.slash.RECEIVE_ERROR_TOAST";

        @Override
        public void onReceive(Context context, Intent intent) {
            String error = intent.getStringExtra(ACTION);
            Toast.makeText(context, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        screen.askForProjectsChats();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(errorToastReceiver);
    }
}
