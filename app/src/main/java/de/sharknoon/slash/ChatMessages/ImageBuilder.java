package de.sharknoon.slash.ChatMessages;

import android.content.Context;
import android.widget.ImageView;

import de.sharknoon.slash.HomeScreen.Chat;

public class ImageBuilder {

    private ImageView imageView;

    public ImageBuilder(Context context, Chat.Message message, ChatOrProject chatOrProject){
        imageView = new ImageView(context);
        imageView.setTag(message.image);

        new ImageLoader(message.image, context, imageView);
    }

    public ImageView getView(){
        return imageView;
    }








}
