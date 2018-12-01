package de.sharknoon.slash.Activties;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;

import de.sharknoon.slash.ChatMessages.ChatMessage;
import de.sharknoon.slash.ChatMessages.UserCreateClientOrProjekt;
import de.sharknoon.slash.HomeScreen.FindUser;
import de.sharknoon.slash.HomeScreen.HomeScreenMessage;
import de.sharknoon.slash.HomeScreen.UserHomeScreen;
import de.sharknoon.slash.R;
import de.sharknoon.slash.SharedPreferences.ParameterManager;

import static de.sharknoon.slash.HomeScreen.UserHomeScreen.homeScreenClient;

public class CreateTemplateActivity extends AppCompatActivity {

    private String chatId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_template);

        Spinner dropdown = findViewById(R.id.template_category);
        String[] items = new String[]{"SUCCESS", "OK", "INFo", "QUESTION", "HELP", "HURRY", "CRITICISM", "INCOMPREHENSION"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        chatId = getIntent().getExtras().getString("CHATID");

        Button send = findViewById(R.id.templates_send);

        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //String sessionId, String chatID, String messageType, String messageContent, String messageSubject, String messageEmotion
                EditText messageContent = findViewById(R.id.templates_messages);
                EditText messageSubject = findViewById(R.id.templates_header);
                Spinner dropdown = findViewById(R.id.template_category);

                Gson gson = new Gson();
                ChatMessage chatMessage = new ChatMessage(ParameterManager.getSession(v.getContext()), chatId ,"EMOTION", messageContent.getText().toString(), messageSubject.getText().toString(), dropdown.getSelectedItem().toString());
                String jsonChatMessage = gson.toJson(chatMessage);
                Log.d("JSON", jsonChatMessage);

                if(homeScreenClient != null){
                    homeScreenClient.getWebSocketClient().send(jsonChatMessage);
                }

                finish();
            }
        });
    }
}
