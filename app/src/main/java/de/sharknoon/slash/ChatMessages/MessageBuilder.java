package de.sharknoon.slash.ChatMessages;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import de.sharknoon.slash.HomeScreen.Chat;
import de.sharknoon.slash.R;

public class MessageBuilder {

    private View view;
    private Chat.Message message;
    private Context context;
    private String ownId;
    private ArrayList<Sender> senders;

    public MessageBuilder(Context context, Chat.Message message, Chat chat){
        view = new LinearLayout(context);
        this.message = message;
        this.context = context;

        senders = new ArrayList<>();

        Sender persA = new Sender(chat.getPartnerUsername(), chat.getPersonA());
        Sender persB = new Sender("You", chat.getPersonB());
        this.senders.add(persA);
        this.senders.add(persB);

        this.ownId = chat.getPersonA();

        createChatMessage();
    }

    public View getView(){
        return view;
    }

    private void createChatMessage() {

        String sender = "";
        String messageToSend = "";
        String headlineColor = "#000000";
        int logo = R.drawable.logo2;

        for (Sender s:senders) {
            if(s.id.equals(message.sender)){
                sender = s.name;
            }
        }

        if(message.type.equals("TEXT")){
            messageToSend = message.content;
        } else if(message.type.equals("EMOTION")) {
            messageToSend = message.subject + ":\n" + message.content;
            switch (message.emotion) {
                case "SUCCESS":
                    logo = R.drawable.ic_checkmark_full;
                    headlineColor = "#049F06";
                    break;
                case "OK":
                    logo = R.drawable.ic_thumps_up_full;
                    headlineColor = "#049F06";
                    break;
                case "INFO":
                    logo = R.drawable.ic_info_full;
                    headlineColor = "#1155cc";
                    break;
                case "QUESTION":
                    logo = R.drawable.ic_help_full;
                    headlineColor = "#1155cc";
                    break;
                case "HELP":
                    logo = R.drawable.ic_man_full;
                    headlineColor = "#1155cc";
                    break;
                case "HURRY":
                    logo = R.drawable.ic_warning_full;
                    headlineColor = "#ef6c00";
                    break;
                case "CRITICISM":
                    logo = R.drawable.ic_warning_round_full;
                    headlineColor = "#990000";
                    break;
                case "INCOMPREHENSION":
                    logo = R.drawable.ic_bold_round_full;
                    headlineColor = "#990000";
                    break;
            }
        }
        LinearLayout layoutBase = new LinearLayout(context);

        TextView header = new TextView(context);
        TextView content = new TextView(context);

        layoutBase.setOrientation(LinearLayout.VERTICAL);
        layoutBase.setGravity(Gravity.RIGHT);
        layoutBase.setBackgroundColor(R.drawable.layout_orange_border);

        header.setText(sender);
        header.setTextColor(Color.parseColor(headlineColor));

        content.setText(messageToSend);

        layoutBase.addView(header);
        layoutBase.addView(content);

        view = layoutBase;
    }

    public class Sender{
        public String name;
        public String id;

        public Sender(String name, String id){
            this.name = name;
            this.id = id;
        }
    }
}
