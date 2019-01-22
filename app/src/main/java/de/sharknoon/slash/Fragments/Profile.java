package de.sharknoon.slash.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import de.sharknoon.slash.ChatMessages.ImageLoader;
import de.sharknoon.slash.Image.ImageSender;
import de.sharknoon.slash.Image.SentimentLoader;
import de.sharknoon.slash.Login.LogoutMessage;
import de.sharknoon.slash.People.GetUserMessage;
import de.sharknoon.slash.People.Person;
import de.sharknoon.slash.R;
import de.sharknoon.slash.SharedPreferences.ParameterManager;

import static android.app.Activity.RESULT_OK;
import static de.sharknoon.slash.HomeScreen.UserHomeScreen.homeScreenClient;

public class Profile extends Fragment {
    private UserReceiver userReceiver = null;
    private LinearLayout userLayout;
    private CircleImageView userImage;
    private ImageView userMood;
    private TextView userName;

    private final int PICK_IMAGE_REQUEST = 1;

    public static Profile newInstance() {
        Profile fragment = new Profile();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        userReceiver = new UserReceiver();
        IntentFilter inf = new IntentFilter(UserReceiver.ACTION);
        getActivity().registerReceiver(userReceiver, inf);

        userLayout = view.findViewById(R.id.profile_layout);
        userLayout.setVisibility(View.GONE);
        userImage = view.findViewById(R.id.element_picture);
        userMood = view.findViewById(R.id.element_mood);
        userName = view.findViewById(R.id.profile_name);

        Gson gson = new Gson();
        GetUserMessage getUserMessage = new GetUserMessage(ParameterManager.getSession(getContext()), ParameterManager.getUserId(getContext()));
        String jsonChatMessage = gson.toJson(getUserMessage);

        if(homeScreenClient != null && homeScreenClient.getWebSocketClient().isOpen())
            homeScreenClient.getWebSocketClient().send(jsonChatMessage);
        else
            Toast.makeText(getActivity(), getString(R.string.error_socket_not_connected), Toast.LENGTH_LONG).show();

        userImage.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, getString(R.string.activity_chat_screen_select_image)), PICK_IMAGE_REQUEST);
        });

        this.handleLogoutButton(view);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                userImage.setImageBitmap(bitmap);
                new ImageSender(bitmap, getContext(), ImageSender.USER);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleLogoutButton(View view) {
        Button logoutButton = view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(view1 -> {
            //Show confirmation dialog
            AlertDialog alertDialog = new AlertDialog.Builder(view1.getContext()).create();
            alertDialog.setTitle("Logout");
            alertDialog.setMessage("Are you sure you want to log out?");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Log out",
                    (dialog, which) -> {
                        // Logout from server
                        Gson gson = new Gson();
                        LogoutMessage logoutMessage = new LogoutMessage(ParameterManager.getSession(view1.getContext()));
                        String jsonLogoutMessage = gson.toJson(logoutMessage);
                        if (homeScreenClient != null) {
                            homeScreenClient.getWebSocketClient().send(jsonLogoutMessage);
                        }
                        //Remove session id and user id from device
                        ParameterManager.setSession(view1.getContext(), null);
                        ParameterManager.setUserId(view1.getContext(), null);
                        //Close activity, which reveals login window
                        getActivity().finish();
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Cancel",
                    (dialog, which) -> dialog.dismiss());
            alertDialog.show();
        });
    }

    public class UserReceiver extends BroadcastReceiver {
        public static final String ACTION = "de.sharknoon.slash.RECEIVE_USER_RESULT";

        @Override
        public void onReceive(Context context, Intent intent) {
            Person user = (Person) intent.getSerializableExtra(ACTION);
            userName.setText(user.getUsername());
            userLayout.setVisibility(View.VISIBLE);
            new ImageLoader(user.getImage(), getContext(), userImage);
            new SentimentLoader(user.getSentiment(), userMood);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(userReceiver);
    }
}
