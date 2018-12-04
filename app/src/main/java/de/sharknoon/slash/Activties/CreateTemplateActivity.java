package de.sharknoon.slash.Activties;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import de.sharknoon.slash.ChatMessages.UserChatScreen;
import de.sharknoon.slash.R;

public class CreateTemplateActivity extends AppCompatActivity {

    private UserChatScreen screen;
    private String id;
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_template);

        Spinner dropdown = findViewById(R.id.template_category);
        String[] items = new String[]{"Erfolg", "Zustimmung", "Information", "Frage", "Benötige Hilfe", "Dringend", "Kritik", "Unverständnis"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setSelection(0);

        if(getIntent().getExtras() != null) {
            screen = (UserChatScreen) getIntent().getExtras().getSerializable("USERCHATSCREEN");
            id = getIntent().getExtras().getString("ID");
            status = getIntent().getExtras().getString("STATUS");
        }

        this.handleSendButton();
    }

    private void handleSendButton() {
        Button send = findViewById(R.id.templates_send);
        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //String sessionId, String chatID, String messageType, String messageContent, String messageSubject, String messageEmotion
                EditText messageContent = findViewById(R.id.templates_messages);
                EditText messageSubject = findViewById(R.id.templates_header);
                Spinner dropdown = findViewById(R.id.template_category);
                String chosenWord = dropdown.getSelectedItem().toString();
                ArrayAdapter Adap = (ArrayAdapter) dropdown.getAdapter();
                int pos = Adap.getPosition(chosenWord);

                boolean input_ok = true;
                if(messageSubject.getText().toString().isEmpty()) {
                    messageSubject.setError(getString(R.string.subject_required));
                    input_ok = false;
                }
                if(messageContent.getText().toString().isEmpty()) {
                    messageContent.setError(getString(R.string.message_required));
                    input_ok = false;
                }
                if(screen != null && input_ok){
                    screen.sendMessage(1,v.getContext(), id, status, messageContent.getText().toString(), getEmotion(pos), messageSubject.getText().toString());
                    finish();
                }
            }
        });
    }

    private String getEmotion(int e){
        String emotion = "";
        switch (e){
            case 0:
                emotion = "SUCCESS";
                break;
            case 1:
                emotion = "OK";
                break;
            case 2:
                emotion = "INFO";
                break;
            case 3:
                emotion = "QUESTION";
                break;
            case 4:
                emotion = "HELP";
                break;
            case 5:
                emotion = "HURRY";
                break;
            case 6:
                emotion = "CRITICISM";
                break;
            case 7:
                emotion = "INCOMPREHENSION";
        }
        return emotion;
    }
}
