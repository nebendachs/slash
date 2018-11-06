package de.sharknoon.slash.Activties;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import de.sharknoon.slash.HomeScreen.ChatMessage;
import de.sharknoon.slash.HomeScreen.ContactView;
import de.sharknoon.slash.HomeScreen.HomeScreenClient;
import de.sharknoon.slash.HomeScreen.UserHomeScreen;
import de.sharknoon.slash.Login.LoginResponseHandler;
import de.sharknoon.slash.R;

public class ChatScreenActivity extends AppCompatActivity {

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        this.context = this;

        // Get the session id from Intent
        Bundle bundle = getIntent().getExtras();
        String[] messages = bundle.getStringArray("messages");
        fillChatScreen(messages);

        this.handleSendButton();
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
        Button sendButton = findViewById(R.id.chatscreen_send_message);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textMessage = findViewById(R.id.chatscreen_text_message);
                String message = textMessage.getText().toString();

                //If Empty Message
                if(message.isEmpty()){
                    return;
                }

                // Get the parameters and send a message
                Bundle bundle = getIntent().getExtras();
                String chatID = bundle.getString("chatID");
                String contactID = bundle.getString(ContactView.CONTACT_ID_PARAMETER);
                String sessionId = UserHomeScreen.sessionId;

                HomeScreenClient client = UserHomeScreen.homeScreenClient;

                Gson gson = new Gson();
                ChatMessage chat = new ChatMessage(sessionId,chatID, message);
                String jsonChatMessage = gson.toJson(chat);
                Log.d("JSON", jsonChatMessage);

                if(client != null){
                    client.getWebSocketClient().send(jsonChatMessage);
                }

            }
        });
    }
}
