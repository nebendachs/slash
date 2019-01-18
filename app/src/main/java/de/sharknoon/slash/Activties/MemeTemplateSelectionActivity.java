package de.sharknoon.slash.Activties;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.sharknoon.slash.R;

public class MemeTemplateSelectionActivity extends AppCompatActivity {
    private final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_template_selection);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View memeGeneratorTemplateContainer = findViewById(R.id.memeGeneratorTemplateContainer);

        // Loop through all image views of the template container and a click listener
        for(int i = 0; i < ((ViewGroup)memeGeneratorTemplateContainer).getChildCount(); i++){
            View nextChild = ((ViewGroup)memeGeneratorTemplateContainer).getChildAt(i);
            int templateIndex = i+1;

            nextChild.setOnClickListener(v -> {
                int memeTemplateIndex = 0;
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
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), memeTemplateIndex);
                moveToMemeGenerator(bitmap);
            });
        }

        TextView custom = findViewById(R.id.custom_image);
        custom.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, getString(R.string.activity_chat_screen_select_image)), PICK_IMAGE_REQUEST);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                moveToMemeGenerator(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

    private void moveToMemeGenerator(Bitmap bitmap){
        Intent goToMemeGenerator = new Intent(getApplicationContext(), MemeGenerationActivity.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out);
        goToMemeGenerator.putExtra("bitmap", out.toByteArray());
        startActivity(goToMemeGenerator);
    }
}
