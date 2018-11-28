package de.sharknoon.slash.Activties;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import de.sharknoon.slash.ChatMessages.ChatMessage;
import de.sharknoon.slash.HomeScreen.ContactView;
import de.sharknoon.slash.HomeScreen.HomeScreenClient;
import de.sharknoon.slash.HomeScreen.UserHomeScreen;
import de.sharknoon.slash.R;
import de.sharknoon.slash.SharedPreferences.ParameterManager;

public class ChatScreenActivity extends AppCompatActivity {

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        Button btn = findViewById(R.id.chatscreen_button_addon);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                moveAddonScreenUpDown();
            }
        });

        this.handleSendButton();
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

    //Fill the Layout with all messages got from server
    public static void fillChatScreen(String[] messages){
        ((Activity) context).runOnUiThread(() -> {
        if(messages != null) {
            if (messages.length > 0) {
                LinearLayout messageScreen = ((Activity) context).findViewById(R.id.chatscreen_message_screen);

                messageScreen.removeAllViews();

                for (String s : messages) {
                    Log.i("FillScreen", s);
                    TextView view = createTextView(s, context);

                        messageScreen.addView(view);
                }
            }
        } else {
            Log.i("messages", "Messages = null");
        }
        });
    }

    //Add one single message
    public static void addMessageToScreen(String s, Context context){
        LinearLayout messageScreen = ((Activity) context).findViewById(R.id.chatscreen_message_screen);
        TextView view = createTextView(s, context);
        messageScreen.addView(view);
    }

    //Method to create and design the text-messages
    private static TextView createTextView(String message, Context context){

        TextView view = new TextView(context);
        view.setText(message);

        //ToDo: Chatnachrichten anpassen (runde Ecken, Hintergrundfarbe etc.)

        return view;
    }

    //Listener for "onClick" on button to send messages
    private void handleSendButton(){
        /*
        Button sendButton = findViewById(R.id.chatscreen_send_message);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Add message class

                HomeScreenClient client = UserHomeScreen.homeScreenClient;
                Gson gson = new Gson();
                String jsonChatMessage = gson.toJson(null);
                Log.d("JSON", jsonChatMessage);

                if(client != null){
                    client.getWebSocketClient().send(jsonChatMessage);
                }

            }
        });
        */
    }
}
