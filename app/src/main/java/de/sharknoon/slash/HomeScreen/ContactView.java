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

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import de.sharknoon.slash.Activties.ChatScreenActivity;
import de.sharknoon.slash.ChatMessages.ChatOrProject;
import de.sharknoon.slash.R;
import de.sharknoon.slash.SharedPreferences.ParameterManager;
import de.sharknoon.slash.Utilities;

public class ContactView {

    private final int TEXT_VIEW_WIDTH_IN_PX = 75;
    public static final String CONTACT_ID_PARAMETER = "contactId";
    public static final String PROJECT_ID_PARAMETER = "projectId";

    public ContactView(Activity homeScreenActivity, LinearLayout parentLayout, String projectName, Project project) {

        homeScreenActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Create TextView with image
                TextView contactTextView = ContactView.this.createContactTextView(projectName, homeScreenActivity, projectName);
                LinearLayout.LayoutParams contactTextViewParams = new LinearLayout.LayoutParams(getTextViewWidth(homeScreenActivity, TEXT_VIEW_WIDTH_IN_PX), LinearLayout.LayoutParams.WRAP_CONTENT);
                contactTextViewParams.setMarginStart(20);
                contactTextView.setLayoutParams(contactTextViewParams);
                parentLayout.addView(contactTextView);
                ChatOrProject chatOrProject = new ChatOrProject(null, project);

                contactTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Go to HomeScreen ChatView and pass the contact Id
                        ParameterManager.setCurrentOpenChatOrProject(chatOrProject);
                        Intent intent = new Intent(homeScreenActivity, ChatScreenActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("CHATORPROJECT", chatOrProject);
                        bundle.putString("NAME", projectName);
                        intent.putExtras(bundle);
                        homeScreenActivity.startActivity(intent);
                    }
                });
            }
        });
    }

    public ContactView(Activity homeScreenActivity, FlexboxLayout parentLayout,String contactName, Chat chat) {

        homeScreenActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Create TextView with image
                TextView contactTextView = ContactView.this.createContactTextView(contactName, homeScreenActivity, contactName);

                LinearLayout.LayoutParams contactTextViewParams = new LinearLayout.LayoutParams(getTextViewWidth(homeScreenActivity, TEXT_VIEW_WIDTH_IN_PX), LinearLayout.LayoutParams.WRAP_CONTENT);
                contactTextViewParams.setMarginStart(20);
                contactTextViewParams.setMargins(0, 50, 0, 0);
                contactTextView.setLayoutParams(contactTextViewParams);
                parentLayout.addView(contactTextView);
                ChatOrProject chatOrProject = new ChatOrProject(chat, null);

                contactTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Go to HomeScreen ChatView and pass the contact Id
                        ParameterManager.setCurrentOpenChatOrProject(chatOrProject);
                        Intent intent = new Intent(homeScreenActivity, ChatScreenActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("CHATORPROJECT", chatOrProject);
                        bundle.putString("NAME", contactName);
                        intent.putExtras(bundle);
                        homeScreenActivity.startActivity(intent);
                    }
                });
            }
        });
    }

    private int loadImageAsync(String imageUrl) {

        int imageInt;

        if(imageUrl == null || imageUrl.isEmpty()){
            return imageInt = R.mipmap.ic_launcher;
        }

        switch (imageUrl) {

            case "Scala":
                imageInt = R.mipmap.ic_scala_round;
                break;
            case "AUME":
                imageInt =  R.mipmap.ic_launcher;
                break;
            case "Mobile Computing":
                imageInt = R.mipmap.ic_mobile_computing_round;
                break;

            case "sharknoon":
                imageInt = R.mipmap.ic_sharknoon_round;
                break;

            case "danielHipp":
                imageInt = R.mipmap.ic_daniel_hipp_round;
                break;

             default:
                imageInt = R.mipmap.ic_launcher;
                break;
        }

        return imageInt;
    }

    private TextView createContactTextView(String text, Activity homeScreenActivity, String imageUrl) {
        // Create TextView with image
        TextView contactTextView = new TextView(homeScreenActivity);
        contactTextView.setText(text);
        contactTextView.setEllipsize(TextUtils.TruncateAt.END);
        int imageId = this.loadImageAsync(imageUrl);
        contactTextView.setCompoundDrawablesWithIntrinsicBounds(0, imageId, 0, 0);
        contactTextView.setGravity(Gravity.CENTER);
        return contactTextView;
    }

    private int getTextViewWidth(Activity homeScreenActivity, int widthInPx) {
        DisplayMetrics metrics = new DisplayMetrics();
        homeScreenActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return Utilities.convertIntoDP(widthInPx, metrics);
    }
}
