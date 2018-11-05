package de.sharknoon.slash.HomeScreen;

import android.app.Activity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import de.sharknoon.slash.R;
import de.sharknoon.slash.Utilities;

public class ContactView {

    private final int TEXT_VIEW_WIDTH_IN_PX = 75;

    public ContactView(Activity homeScreenActivity, LinearLayout parentLayout, String imageUrl, String contactName) {

        homeScreenActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Create TextView with image
                TextView contactTextView = ContactView.this.createContactTextView(contactName, homeScreenActivity);
                LinearLayout.LayoutParams contactTextViewParams = new LinearLayout.LayoutParams(getTextViewWidht(homeScreenActivity, TEXT_VIEW_WIDTH_IN_PX ), LinearLayout.LayoutParams.WRAP_CONTENT);
                contactTextViewParams.setMarginStart(20);
                contactTextView.setLayoutParams(contactTextViewParams);
                parentLayout.addView(contactTextView);
            }
        });
    }

    public ContactView(Activity homeScreenActivity, FlexboxLayout parentLayout, String imageUrl, String contactName) {

        homeScreenActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Create TextView with image
                TextView contactTextView = ContactView.this.createContactTextView(contactName, homeScreenActivity);

                LinearLayout.LayoutParams contactTextViewParams = new LinearLayout.LayoutParams(getTextViewWidht(homeScreenActivity, TEXT_VIEW_WIDTH_IN_PX ), LinearLayout.LayoutParams.WRAP_CONTENT);
                contactTextViewParams.setMarginStart(20);
                contactTextViewParams.setMargins(0,50,0,0);
                contactTextView.setLayoutParams(contactTextViewParams);
                parentLayout.addView(contactTextView);
            }
        });
    }

    private void loadImageAsync() {


    }

    private TextView createContactTextView(String text, Activity homeScreenActivity){
        // Create TextView with image
        TextView contactTextView = new TextView(homeScreenActivity);
        contactTextView.setText(text);
        contactTextView.setEllipsize(TextUtils.TruncateAt.END);
        contactTextView.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.ic_launcher_round, 0, 0);
        contactTextView.setGravity(Gravity.CENTER);
        return contactTextView;
    }

    private int getTextViewWidht(Activity homeScreenActivity, int widthInPx){
        DisplayMetrics metrics = new DisplayMetrics();
        homeScreenActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return Utilities.convertIntoDP(widthInPx, metrics);
    }
}
