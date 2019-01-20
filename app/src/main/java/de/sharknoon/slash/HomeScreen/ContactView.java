package de.sharknoon.slash.HomeScreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import de.hdodenhof.circleimageview.CircleImageView;
import de.sharknoon.slash.Activties.ChatScreenActivity;
import de.sharknoon.slash.ChatMessages.ChatOrProject;
import de.sharknoon.slash.ChatMessages.ImageLoader;
import de.sharknoon.slash.Image.SentimentLoader;
import de.sharknoon.slash.People.Person;
import de.sharknoon.slash.R;
import de.sharknoon.slash.SharedPreferences.ParameterManager;
import de.sharknoon.slash.Utilities;

class ContactView {

    private static final int TEXT_VIEW_WIDTH_IN_PX = 75;
    private static final int CONTACT = 0;
    private static final int PROJECT = 1;

    public ContactView(Activity homeScreenActivity, LinearLayout parentLayout, String projectName, Project project) {

        homeScreenActivity.runOnUiThread(() -> {
            FrameLayout frameLayout = new FrameLayout(homeScreenActivity);

            // Create TextView with image
            LinearLayout contactTextView = createContactTextView(projectName, homeScreenActivity, project.getImage(), PROJECT);
            frameLayout.addView(contactTextView);

            // Create ImageView with mood image
            if(project.getSentiment() != null) {
                ImageView mood = createMoodImageView(project.getSentiment(), homeScreenActivity);
                frameLayout.addView(mood);
            }

            parentLayout.addView(frameLayout);
            ChatOrProject chatOrProject = new ChatOrProject(null, project);

            contactTextView.setOnClickListener(v -> {
                // Go to HomeScreen ChatView and pass the contact Id
                ParameterManager.setCurrentOpenChatOrProject(chatOrProject);
                Intent intent = new Intent(homeScreenActivity, ChatScreenActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("CHATORPROJECT", chatOrProject);
                bundle.putString("NAME", projectName);
                intent.putExtras(bundle);
                homeScreenActivity.startActivity(intent);
            });
        });
    }

    public ContactView(Activity homeScreenActivity, FlexboxLayout parentLayout, String contactName, Chat chat) {

        homeScreenActivity.runOnUiThread(() -> {
            FrameLayout frameLayout = new FrameLayout(homeScreenActivity);

            // Create TextView with image
            LinearLayout contactTextView = createContactTextView(contactName, homeScreenActivity, chat.getPartnerImage(), CONTACT);
            frameLayout.addView(contactTextView);

            // Create ImageView with mood image
            if(chat.getSentiment() != null) {
                ImageView mood = createMoodImageView(chat.getSentiment(), homeScreenActivity);
                frameLayout.addView(mood);
            }

            parentLayout.addView(frameLayout);
            ChatOrProject chatOrProject = new ChatOrProject(chat, null);

            contactTextView.setOnClickListener(v -> {
                // Go to HomeScreen ChatView and pass the contact Id
                ParameterManager.setCurrentOpenChatOrProject(chatOrProject);
                Intent intent = new Intent(homeScreenActivity, ChatScreenActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("CHATORPROJECT", chatOrProject);
                bundle.putString("NAME", contactName);
                intent.putExtras(bundle);
                homeScreenActivity.startActivity(intent);
            });
        });
    }

    private ImageView createMoodImageView(Person.Sentiment sentiment, Activity homeScreenActivity) {
        ImageView mood = new ImageView(homeScreenActivity);
        new SentimentLoader(sentiment, mood);
        int dimensions = (int)(Math.ceil(getTextViewWidth(homeScreenActivity)*0.6));
        FrameLayout.LayoutParams imageViewParams = new FrameLayout.LayoutParams(dimensions, dimensions);
        imageViewParams.setMarginStart(15);
        mood.setLayoutParams(imageViewParams);
        return mood;
    }

    private LinearLayout createContactTextView(String text, Activity homeScreenActivity, String imageUrl, int contactorproject) {
        LinearLayout layout = new LinearLayout(homeScreenActivity);
        layout.setOrientation(LinearLayout.VERTICAL);

        CircleImageView image = new CircleImageView(homeScreenActivity);
        if(contactorproject == PROJECT)
            image.setImageResource(R.drawable.logo);
        else
            image.setImageResource(R.drawable.ic_default_profile);
        new ImageLoader(imageUrl, homeScreenActivity, image);
        int dimensions = getTextViewWidth(homeScreenActivity);
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

    private int getTextViewWidth(Activity homeScreenActivity) {
        DisplayMetrics metrics = new DisplayMetrics();
        homeScreenActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return Utilities.convertIntoDP(ContactView.TEXT_VIEW_WIDTH_IN_PX, metrics);
    }
}
