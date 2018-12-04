package de.sharknoon.slash.Activties;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import de.sharknoon.slash.Fragments.Home;
import de.sharknoon.slash.Fragments.Profile;
import de.sharknoon.slash.HomeScreen.UserHomeScreen;
import de.sharknoon.slash.R;

public class HomeScreenActivity extends AppCompatActivity implements Home.OnFragmentInteractionListener, Profile.OnFragmentInteractionListener{
    private UserHomeScreen screen;
    private FragmentManager fragmentManager;
    private Fragment home;
    private Fragment profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        screen = new UserHomeScreen(this);

        fragmentManager = getSupportFragmentManager();

        // define your fragments here
        home = Home.newInstance();
        profile = Profile.newInstance();

        FragmentTransaction fragmentTransaction;
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, home).commit();

        this.handleBottomNavigation();
        this.handlePullRefresh();


    }

    private void handleBottomNavigation() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment, home).commit();
                        return true;
                    case R.id.navigation_profile:
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment, profile).commit();
                        return true;
                }
                return false;
            }
        });
    }

    private void handlePullRefresh() {
        SwipeRefreshLayout swipe = findViewById(R.id.coordinatorLayout);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                screen.askForProjectsChats();
                swipe.setRefreshing(false);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        screen.askForProjectsChats();
    }

    @Override
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }
}
