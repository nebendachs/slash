package de.sharknoon.slash.Activties;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;

import de.sharknoon.slash.ChatMessages.ChatOrProject;
import de.sharknoon.slash.ChatMessages.SendChatMessage;
import de.sharknoon.slash.ChatMessages.SendProjectMessage;
import de.sharknoon.slash.HomeScreen.UserHomeScreen;
import de.sharknoon.slash.Image.UploadImageMessage;
import de.sharknoon.slash.MemeGenerator.MemeGeneration;
import de.sharknoon.slash.R;
import de.sharknoon.slash.SharedPreferences.ParameterManager;

public class MemeGenerationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_generation_actitivity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get template index from Intent
        Intent intent = getIntent();
        int templateIndex = intent.getIntExtra("drawable", 0);
        String chatId = intent.getStringExtra("chatId");
        ImageView memeGeneratorImageView = findViewById(R.id.memeGeneratorImageView);

        int memeTemplateIndex = 0;

        // Show template dependent on the index
        switch (templateIndex) {

            case 1:
                memeTemplateIndex = R.drawable.memetemplate1;
                break;

            case 2:
                memeTemplateIndex = R.drawable.memetemplate2;
                break;

            case 3:
                memeTemplateIndex = R.drawable.memetemplate3;
                break;

            case 4:
                memeTemplateIndex = R.drawable.memetemplate4;
                break;

            case 5:
                memeTemplateIndex = R.drawable.memetemplate5;
                break;

            case 6:
                memeTemplateIndex = R.drawable.memetemplate6;
                break;

            case 7:
                memeTemplateIndex = R.drawable.memetemplate7;
                break;

            case 8:
                memeTemplateIndex = R.drawable.memetemplate8;
                break;

            case 9:
                memeTemplateIndex = R.drawable.memetemplate9;
                break;

            case 10:
                memeTemplateIndex = R.drawable.memetemplate10;
                break;

            case 11:
                memeTemplateIndex = R.drawable.memetemplate11;
                break;

            case 12:
                memeTemplateIndex = R.drawable.memetemplate12;
                break;
        }

        memeGeneratorImageView.setImageResource(memeTemplateIndex);

        // Get text view and edit text from activity layout
        TextView textViewBottomMessage = findViewById(R.id.memeGeneratorTextViewBottomMessage);
        TextView textViewUpperMessage = findViewById(R.id.memeGeneratorTextViewUpperMessage);

        // Set the typical meme font
        Typeface memeFont = Typeface.createFromAsset(getAssets(), "fonts/impact.ttf");
        textViewBottomMessage.setTypeface(memeFont);
        textViewUpperMessage.setTypeface(memeFont);

        EditText editTextBottomMessage = findViewById(R.id.memeGeneratorEditTextBottomMessage);
        EditText editTextUpperMessage = findViewById(R.id.memeGeneratorEditTextUpperMessage);

        // Set listener for the bottom message input
        editTextBottomMessage.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                // Insert input of user in message text view over the meme template
                textViewBottomMessage.setText(s.toString().toUpperCase());
            }
        });

        // Set listener for the top message input
        editTextUpperMessage.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                // Insert input of user in message text view over the meme template
                textViewUpperMessage.setText(s.toString().toUpperCase());
            }
        });

        // Trigger Meme Generation by execute button
        Button generateMemeButton = findViewById(R.id.memeGeneratorButton);
        int finalMemeTemplateIndex = memeTemplateIndex;

        // Generate Meme
        generateMemeButton.setOnClickListener(v -> {
            String upperMessage = editTextUpperMessage.getText().toString().toUpperCase();
            String belowMessage = editTextBottomMessage.getText().toString().toUpperCase();
            Bitmap meme = MemeGeneration.createMeme(finalMemeTemplateIndex, upperMessage, belowMessage, MemeGenerationActivity.this);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            meme.compress(Bitmap.CompressFormat.PNG, 100, output);
            byte[] memeInBytes = output.toByteArray();
            UploadImageMessage.setImageData(memeInBytes);

            ChatOrProject currentChatOrProject = ParameterManager.getCurrentOpenChatOrProject();

            Gson gson = new Gson();
            String message;
            if(currentChatOrProject.getProject() != null){

                SendProjectMessage projectMessage = new SendProjectMessage(ParameterManager.getSession(this),
                        currentChatOrProject.getProject().getId(), "IMAGE", "", "", "", "ADD_PROJECT_MESSAGE");
                message = gson.toJson(projectMessage);

            } else {
                SendChatMessage chatMessage = new SendChatMessage(ParameterManager.getSession(this),
                        currentChatOrProject.getChat().getId(), "IMAGE", "", "", "", "ADD_CHAT_MESSAGE");
                message = gson.toJson(chatMessage);
            }

            UserHomeScreen.homeScreenClient.getWebSocketClient().send(message);

            Intent backToHomeScreenIntent = new Intent(this,LoginActivity.class);
            backToHomeScreenIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(backToHomeScreenIntent);
         });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}

