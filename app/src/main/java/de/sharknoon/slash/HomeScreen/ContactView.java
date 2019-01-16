package de.sharknoon.slash.HomeScreen;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import de.sharknoon.slash.Activties.ChatScreenActivity;
import de.sharknoon.slash.ChatMessages.ChatOrProject;
import de.sharknoon.slash.ChatMessages.ImageLoader;
import de.sharknoon.slash.Image.SentimentLoader;
import de.sharknoon.slash.People.Person;
import de.sharknoon.slash.R;
import de.sharknoon.slash.SharedPreferences.ParameterManager;
import de.sharknoon.slash.Utilities;

public class ContactView {

    public static final int TEXT_VIEW_WIDTH_IN_PX = 75;
    public static final String CONTACT_ID_PARAMETER = "contactId";
    public static final String PROJECT_ID_PARAMETER = "projectId";

    public ContactView(Activity homeScreenActivity, LinearLayout parentLayout, String projectName, Project project) {

        homeScreenActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                FrameLayout frameLayout = new FrameLayout(homeScreenActivity);

                // Create TextView with image
                LinearLayout contactTextView = createContactTextView(projectName, homeScreenActivity, project.getImage());
                frameLayout.addView(contactTextView);

                // Create ImageView with mood image
                if(project.getSentiment() != null) {
                    ImageView mood = createMoodImageView(project.getSentiment(), homeScreenActivity);
                    frameLayout.addView(mood);
                }

                parentLayout.addView(frameLayout);
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

    public ContactView(Activity homeScreenActivity, FlexboxLayout parentLayout, String contactName, Chat chat) {

        homeScreenActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                FrameLayout frameLayout = new FrameLayout(homeScreenActivity);

                // Create TextView with image
                LinearLayout contactTextView = createContactTextView(contactName, homeScreenActivity, chat.getPartnerImage());
                frameLayout.addView(contactTextView);

                // Create ImageView with mood image
                //todo: Get sentiment for chats
                if(chat.getSentiment() != null) {
                    ImageView mood = createMoodImageView(chat.getSentiment(), homeScreenActivity);
                    frameLayout.addView(mood);
                }

                parentLayout.addView(frameLayout);
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

    private ImageView createMoodImageView(Person.Sentiment sentiment, Activity homeScreenActivity) {
        ImageView mood = new ImageView(homeScreenActivity);
        new SentimentLoader(sentiment, mood);
        int dimensions = (int)(Math.ceil(getTextViewWidth(homeScreenActivity, TEXT_VIEW_WIDTH_IN_PX)*0.6));
        FrameLayout.LayoutParams imageViewParams = new FrameLayout.LayoutParams(dimensions, dimensions);
        imageViewParams.setMarginStart(15);
        mood.setLayoutParams(imageViewParams);
        return mood;
    }

    private LinearLayout createContactTextView(String text, Activity homeScreenActivity, String imageUrl) {
        LinearLayout layout = new LinearLayout(homeScreenActivity);
        layout.setOrientation(LinearLayout.VERTICAL);

        CircleImageView image = new CircleImageView(homeScreenActivity);
        new ImageLoader(imageUrl, homeScreenActivity, image);
        int dimensions = getTextViewWidth(homeScreenActivity, TEXT_VIEW_WIDTH_IN_PX);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dimensions, dimensions);
        image.setLayoutParams(layoutParams);
        layout.addView(image);

        TextView contactTextView = new TextView(homeScreenActivity);
        contactTextView.setText(text);
        contactTextView.setWidth(dimensions);
        contactTextView.setMaxLines(2);
        contactTextView.setGravity(Gravity.CENTER);
        layout.addView(contactTextView);

        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams1.setMargins(45, 30, 0, 0);
        layout.setLayoutParams(layoutParams1);
        return layout;
    }

    private int getTextViewWidth(Activity homeScreenActivity, int widthInPx) {
        DisplayMetrics metrics = new DisplayMetrics();
        homeScreenActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return Utilities.convertIntoDP(widthInPx, metrics);
    }
}
