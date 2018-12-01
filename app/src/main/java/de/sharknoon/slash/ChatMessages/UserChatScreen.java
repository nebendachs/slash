package de.sharknoon.slash.ChatMessages;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import de.sharknoon.slash.HomeScreen.Chat;

public class UserChatScreen {

    //Fill the Layout with all messages got from server
    public void fillChatScreen(Chat chat, Context context, LinearLayout messageScreen){
        if(chat != null) {
            if (chat.getMessages().size() > 0) {

                messageScreen.removeAllViews();

                for (Chat.Message s : chat.getMessages()) {
                    MessageBuilder builder = new MessageBuilder(context, s, chat);
                    View view = builder.getView();
                    messageScreen.addView(view);
                }
            }
        } else {
            Log.i("Chat", "Chat = null");
        }
    }
}
