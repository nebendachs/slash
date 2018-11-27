package de.sharknoon.slash.Activties;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import de.sharknoon.slash.Fragments.Home;
import de.sharknoon.slash.Fragments.Profile;
import de.sharknoon.slash.HomeScreen.UserHomeScreen;
import de.sharknoon.slash.R;

public class HomeScreenActivity extends AppCompatActivity implements Home.OnFragmentInteractionListener, Profile.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        UserHomeScreen screen = new UserHomeScreen(this);

        final FragmentManager fragmentManager = getSupportFragmentManager();

        // define your fragments here
        final Fragment home = new Home();
        final Fragment profile = new Profile();

        FragmentTransaction fragmentTransaction;
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, home).commit();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationView);

        // handle navigation selection
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
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

    @Override
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }
}
