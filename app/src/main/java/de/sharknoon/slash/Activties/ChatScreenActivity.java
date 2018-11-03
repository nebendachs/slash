package de.sharknoon.slash.Activties;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.sharknoon.slash.ChatScreen.UserChatScreen;
import de.sharknoon.slash.Login.LoginResponseHandler;
import de.sharknoon.slash.R;

public class ChatScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        this.handleSendButton();
    }

    //Fill the Layout with all messages got from server
    public static void fillChatScreen(String[] messages, Context context){
        if(messages.length > 0){
            LinearLayout messageScreen = ((Activity) context).findViewById(R.id.chatscreen_send_message);

            for (String s: messages) {
                TextView view = createTextView(s, context);

                messageScreen.addView(view);
            }

        }
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
                String message = String.valueOf(textMessage);

                //If Empty Message
                if(message.isEmpty()){
                    return;
                }

                // Get the session id from Intent
                Bundle bundle = getIntent().getExtras();
                String sessionId = bundle.getString(LoginResponseHandler.BUNDLE_KEY_SESSION_ID);

                new UserChatScreen(sessionId, message, v.getContext());
            }
        });
    }
}
