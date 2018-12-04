package de.sharknoon.slash.Activties;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import de.sharknoon.slash.ChatMessages.GetChat;
import de.sharknoon.slash.Fragments.CreateProject;
import de.sharknoon.slash.Fragments.PeopleSelector;
import de.sharknoon.slash.People.Person;
import de.sharknoon.slash.R;
import de.sharknoon.slash.SharedPreferences.ParameterManager;
import de.sharknoon.slash.UISupport.ViewPagerAdapter;

import static de.sharknoon.slash.HomeScreen.UserHomeScreen.homeScreenClient;

public class CreateClientProjektActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ChatPersonReceiver personReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chat_projekt);

        personReceiver = new ChatPersonReceiver();
        personReceiver.setActivity(this);
        IntentFilter intentFilter = new IntentFilter(ChatPersonReceiver.ACTION);
        registerReceiver(personReceiver, intentFilter);

        viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(PeopleSelector.newInstance(PeopleSelector.CHAT, null), "Chat");
        adapter.addFragment(CreateProject.newInstance(), "Project");
        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_person);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_people);
    }

    public class ChatPersonReceiver extends BroadcastReceiver {
        CreateClientProjektActivity activity = null;
        public static final String ACTION = "de.sharknoon.slash.RECEIVE_PERSON_CHAT";

        @Override
        public void onReceive(Context context, Intent intent) {
            Person person = (Person) intent.getSerializableExtra("Person");

            Gson gson = new Gson();
            GetChat getChat = new GetChat(ParameterManager.getSession(context), person.getId());
            String jsonChatMessage = gson.toJson(getChat);

            if(homeScreenClient != null){
                homeScreenClient.getWebSocketClient().send(jsonChatMessage);
            }

            //activity.finish();
        }

        public void setActivity(CreateClientProjektActivity activity) {
            this.activity = activity;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(personReceiver);
    }
}