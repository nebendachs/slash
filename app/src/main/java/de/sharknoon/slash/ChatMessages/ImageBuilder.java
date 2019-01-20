package de.sharknoon.slash.ChatMessages;

import android.content.Context;
import android.widget.ImageView;

import de.sharknoon.slash.HomeScreen.Chat;

class ImageBuilder {

    private final ImageView imageView;

    public ImageBuilder(Context context, Chat.Message message){
        imageView = new ImageView(context);
        imageView.setTag(message.getImage());

        new ImageLoader(message.getImage(), context, imageView);
    }

    public ImageView getView(){
        return imageView;
    }








}
