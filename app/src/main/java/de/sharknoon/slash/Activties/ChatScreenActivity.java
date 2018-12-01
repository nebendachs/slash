package de.sharknoon.slash.Activties;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import de.sharknoon.slash.ChatMessages.ChatMessage;
import de.sharknoon.slash.ChatMessages.MessageBuilder;
import de.sharknoon.slash.ChatMessages.UserChatScreen;
import de.sharknoon.slash.HomeScreen.Chat;
import de.sharknoon.slash.HomeScreen.ContactView;
import de.sharknoon.slash.HomeScreen.HomeScreenClient;
import de.sharknoon.slash.HomeScreen.UserHomeScreen;
import de.sharknoon.slash.R;
import de.sharknoon.slash.SharedPreferences.ParameterManager;

import static de.sharknoon.slash.HomeScreen.UserHomeScreen.homeScreenClient;

public class ChatScreenActivity extends AppCompatActivity {

    private static String chatId;
    private static UserChatScreen screen;
    private static LinearLayout messageScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        Chat chat = (Chat)getIntent().getExtras().getSerializable("CHAT");
        messageScreen = findViewById(R.id.chatscreen_message_screen);

        screen = new UserChatScreen();

        screen.fillChatScreen(chat, this, messageScreen);

        messageScreen = findViewById(R.id.chatscreen_message_screen);

        Button btn = findViewById(R.id.chatscreen_button_addon);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                moveAddonScreenUpDown();
            }
        });

        Button createTemplate = findViewById(R.id.chatscreen_button_template);

        createTemplate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Activity templateActivity = (Activity) v.getContext();
                Intent intent = new Intent(v.getContext(), CreateTemplateActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("CHATID", chat.getId());
                intent.putExtras(bundle);
                templateActivity.startActivity(intent);
            }
        });

        Button createMeme = findViewById(R.id.chatscreen_button_meme);

        createMeme.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //TODO: DO STUFF
            }
        });

        this.handleSendButton(chat);
    }

    public static void setChat(Chat chat, Context context){
        screen.fillChatScreen(chat, context, messageScreen);
    }

    public void moveAddonScreenUpDown(){
        RelativeLayout layout = findViewById(R.id.chatscreen_menu_bottom);

        if(layout.getVisibility() == View.VISIBLE){
            //Show the panel
            Animation bottomUp = AnimationUtils.loadAnimation(this, R.anim.new_chat_message_up);
            layout.startAnimation(bottomUp);
            layout.setVisibility(View.VISIBLE);
        } else {
            //Hide panel
            Animation bottomDown = AnimationUtils.loadAnimation(this, R.anim.new_chat_message_down);
            layout.startAnimation(bottomDown);
            layout.setVisibility(View.GONE);
        }
    }

    //Listener for "onClick" on button to send messages
    private void handleSendButton(Chat chat){
        Button sendButton = findViewById(R.id.chatscreen_button_send);
        EditText editText = findViewById(R.id.chatscreen_message_field);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                ChatMessage chatMessage = new ChatMessage(ParameterManager.getSession(v.getContext()), chat.getId() ,"TEXT", editText.getText().toString(), "", "");
                String jsonChatMessage = gson.toJson(chatMessage);
                Log.d("JSON", jsonChatMessage);

                if(homeScreenClient != null){
                    homeScreenClient.getWebSocketClient().send(jsonChatMessage);
                }

            }
        });
    }
}
