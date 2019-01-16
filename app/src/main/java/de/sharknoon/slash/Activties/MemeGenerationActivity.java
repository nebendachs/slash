package de.sharknoon.slash.Activties;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.ByteArrayInputStream;

import de.sharknoon.slash.Image.ImageSender;
import de.sharknoon.slash.MemeGenerator.MemeGeneration;
import de.sharknoon.slash.R;

public class MemeGenerationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_generation_actitivity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get template index from Intent
        Intent intent = getIntent();
        Bitmap bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(intent.getByteArrayExtra("bitmap")));
        String chatId = intent.getStringExtra("chatId");
        ImageView memeGeneratorImageView = findViewById(R.id.memeGeneratorImageView);

        memeGeneratorImageView.setImageBitmap(bitmap);

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

        // Generate Meme
        generateMemeButton.setOnClickListener(v -> {
            String upperMessage = editTextUpperMessage.getText().toString().toUpperCase();
            String belowMessage = editTextBottomMessage.getText().toString().toUpperCase();
            Bitmap meme = MemeGeneration.createMeme(bitmap, upperMessage, belowMessage, MemeGenerationActivity.this);

            new ImageSender(meme, this, ImageSender.CHATORPROJECT);

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

