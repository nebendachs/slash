package de.sharknoon.slash.Activties;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Objects;

import de.sharknoon.slash.R;

public class MemeTemplateSelectionActivity extends AppCompatActivity {

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
                moveToMemeGenerator(templateIndex);
            });
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

    private void moveToMemeGenerator(int templateIndex){
        Intent goToMemeGenerator = new Intent(getApplicationContext(), MemeGenerationActivity.class);
        goToMemeGenerator.putExtra("drawable", templateIndex);
        startActivity(goToMemeGenerator);
    }
}
