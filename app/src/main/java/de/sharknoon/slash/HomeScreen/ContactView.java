package de.sharknoon.slash.HomeScreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import de.sharknoon.slash.Activties.ChatScreenActivity;
import de.sharknoon.slash.R;
import de.sharknoon.slash.Utilities;

public class ContactView {

    private final int TEXT_VIEW_WIDTH_IN_PX = 75;
    public static final String CONTACT_ID_PARAMETER = "contactId";
    public static final String PROJECT_ID_PARAMETER = "projectId";

    public ContactView(Activity homeScreenActivity, LinearLayout parentLayout, String imageUrl, String projectName, String projectId) {

        homeScreenActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Create TextView with image
                TextView contactTextView = ContactView.this.createContactTextView(projectName, homeScreenActivity);
                LinearLayout.LayoutParams contactTextViewParams = new LinearLayout.LayoutParams(getTextViewWidht(homeScreenActivity, TEXT_VIEW_WIDTH_IN_PX ), LinearLayout.LayoutParams.WRAP_CONTENT);
                contactTextViewParams.setMarginStart(20);
                contactTextView.setLayoutParams(contactTextViewParams);
                parentLayout.addView(contactTextView);

                contactTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Go to HomeScreen ChatView and pass the contact Id
                        Intent intent = new Intent(homeScreenActivity, ChatScreenActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(PROJECT_ID_PARAMETER, projectId);
                        intent.putExtras(bundle);
                        homeScreenActivity.startActivity(intent);
                    }
                });
            }
        });
    }

    public ContactView(Activity homeScreenActivity, FlexboxLayout parentLayout, String imageUrl, String contactName, String contactId) {

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

                contactTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Go to HomeScreen ChatView and pass the contact Id
                        Intent intent = new Intent(homeScreenActivity, ChatScreenActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(CONTACT_ID_PARAMETER, contactId);
                        intent.putExtras(bundle);
                        homeScreenActivity.startActivity(intent);
                    }
                });
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
