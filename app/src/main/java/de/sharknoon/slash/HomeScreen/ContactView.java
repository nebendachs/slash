package de.sharknoon.slash.HomeScreen;

import android.app.Activity;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.sharknoon.slash.R;

public class ContactView {
    public ContactView(Activity homeScreenActivity, LinearLayout parentLayout, String imageUrl, String contactName) {

        homeScreenActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Create TextView with image
                TextView contactTextView = new TextView(homeScreenActivity);
                contactTextView.setText(contactName);
                contactTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.ic_launcher_round, 0, 0);
                contactTextView.setGravity(Gravity.CENTER);
                LinearLayout.LayoutParams contactTextViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                contactTextViewParams.setMarginStart(20);
                contactTextView.setLayoutParams(contactTextViewParams);
                parentLayout.addView(contactTextView);
            }
        });
    }

    private void loadImageAsync() {


    }
}
