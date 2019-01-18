package de.sharknoon.slash.Image;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;

import de.sharknoon.slash.ChatMessages.ChatOrProject;
import de.sharknoon.slash.ChatMessages.SendChatMessage;
import de.sharknoon.slash.ChatMessages.SendProjectMessage;
import de.sharknoon.slash.HomeScreen.UserHomeScreen;
import de.sharknoon.slash.SharedPreferences.ParameterManager;

public class ImageSender {
    public static final int CHATORPROJECT = 0;
    public static final int PROJECT = 1;
    public static final int USER = 2;

    public ImageSender(Bitmap image, Context context, int intent) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        if(intent == PROJECT || intent == USER) {
            // crop to 80x80 square
            if(image.getWidth() > image.getHeight())
                image = Bitmap.createBitmap(image, image.getWidth() / 2 - image.getHeight() / 2, 0, image.getHeight(), image.getHeight());
            else if(image.getWidth() < image.getHeight())
                image = Bitmap.createBitmap(image, 0, image.getHeight() / 2 - image.getWidth() / 2, image.getWidth(), image.getWidth());
            image = Bitmap.createScaledBitmap(image, 100, 100, false);
            image.compress(Bitmap.CompressFormat.JPEG, 100, output);
        } else
            image.compress(Bitmap.CompressFormat.JPEG, 50, output);

        byte[] imageInBytes = output.toByteArray();
        UploadImageMessage.setImageData(imageInBytes);
        Gson gson = new Gson();
        String message = "";

        ChatOrProject currentChatOrProject = ParameterManager.getCurrentOpenChatOrProject();
        switch(intent) {
            case CHATORPROJECT:
                if(currentChatOrProject.getProject() != null) {
                    SendProjectMessage projectMessage = new SendProjectMessage(ParameterManager.getSession(context),
                            currentChatOrProject.getProject().getId(), "IMAGE", "", "", "");
                    message = gson.toJson(projectMessage);
                } else {
                    SendChatMessage chatMessage = new SendChatMessage(ParameterManager.getSession(context),
                            currentChatOrProject.getChat().getId(), "IMAGE", "", "", "");
                    message = gson.toJson(chatMessage);
                }
                break;
            case PROJECT:
                UpdateImageMessage projectImageMessage = new UpdateImageMessage(
                        ParameterManager.getSession(context),
                        currentChatOrProject.getProject().getId(),
                        false);
                message = gson.toJson(projectImageMessage);
                break;
            case USER:
                UpdateImageMessage chatImageMessage = new UpdateImageMessage(
                        ParameterManager.getSession(context),
                        false);
                message = gson.toJson(chatImageMessage);
                break;
        }

        UserHomeScreen.homeScreenClient.getWebSocketClient().send(message);
    }
}
