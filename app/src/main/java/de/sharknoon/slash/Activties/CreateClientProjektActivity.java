package de.sharknoon.slash.Activties;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import de.sharknoon.slash.Fragments.CreateChat;
import de.sharknoon.slash.Fragments.CreateProject;
import de.sharknoon.slash.HomeScreen.UserCreateClientOrProjekt;
import de.sharknoon.slash.R;
import de.sharknoon.slash.UISupport.ViewPagerAdapter;

public class CreateClientProjektActivity extends AppCompatActivity implements CreateChat.OnFragmentInteractionListener, CreateProject.OnFragmentInteractionListener {

    private UserCreateClientOrProjekt ccp;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chat_projekt);

        ccp = new UserCreateClientOrProjekt();

        viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(CreateChat.newInstance(ccp), "Chat");
        adapter.addFragment(CreateProject.newInstance(), "Project");
        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_person);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_people);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
