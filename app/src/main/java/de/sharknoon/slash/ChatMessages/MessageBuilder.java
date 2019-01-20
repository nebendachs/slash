package de.sharknoon.slash.ChatMessages;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import de.sharknoon.slash.HomeScreen.Chat;
import de.sharknoon.slash.HomeScreen.Project;
import de.sharknoon.slash.People.Person;
import de.sharknoon.slash.R;
import de.sharknoon.slash.SharedPreferences.ParameterManager;

class MessageBuilder {
    private View view;
    private final Chat.Message message;
    private final Context context;
    private Project project;
    private boolean project_b;
    private final ArrayList<Sender> senders;

    public MessageBuilder(Context context, Chat.Message message, ChatOrProject chatOrProject){
        view = new LinearLayout(context);
        this.message = message;
        this.context = context;

        senders = new ArrayList<>();

        if (chatOrProject.isProject()){
            project_b = true;

            project = chatOrProject.getProject();
            for (Person user:project.getUsernames()){
                Sender sender = new Sender(user.getUsername(), user.getId());
                this.senders.add(sender);
            }
        } else {
            Chat chat = chatOrProject.getChat();
            Sender persA = new Sender("", chat.getPersonA());
            Sender persB = new Sender("", chat.getPersonB());
            this.senders.add(persA);
            this.senders.add(persB);
        }

        createChatMessage();
    }

    public View getView(){
        return view;
    }

    private void createChatMessage() {

        String sender = "";
        String messageToSend = "";
        String emotionToSend = "";
        int headlineColor = R.color.colorPrimary;
        int logo = R.drawable.logo2;

        boolean left_b = true;
        boolean template_b = false;

        for (Sender s:senders) {
            if(s.id.equals(message.getSender())){
                if(project_b) {
                    sender = s.name;
                }
                if(s.id.equals(ParameterManager.getUserId(context))){
                    left_b = false;
                }
            }
        }

        Log.d("asdf", "Sender: " + message.getSender());
        if(project != null)
        Log.d("asdf", "Srum Master: " + project.getProjectOwner());

        if(message.getType().equals("TEXT")){
            if(message.getSender().equals(project.getProjectOwner()))

            messageToSend = message.getContent();
        } else if(message.getType().equals("EMOTION")) {
            template_b = true;
            messageToSend = "#"+message.getSubject() + ":\n" + message.getContent();
            switch (message.getEmotion()) {
                case "SUCCESS":
                    logo = R.drawable.ic_checkmark_full;
                    headlineColor = R.color.colorSuccess;
                    emotionToSend = "Erfolg";
                    break;
                case "OK":
                    logo = R.drawable.ic_thumps_up_full;
                    headlineColor = R.color.colorOk;
                    emotionToSend = "Zustimmung";
                    break;
                case "INFO":
                    logo = R.drawable.ic_info_full;
                    headlineColor = R.color.colorInfo;
                    emotionToSend = "Information";
                    break;
                case "QUESTION":
                    logo = R.drawable.ic_help_full;
                    headlineColor = R.color.colorQuestion;
                    emotionToSend = "Frage";
                    break;
                case "HELP":
                    logo = R.drawable.ic_man_full;
                    headlineColor = R.color.colorHelp;
                    emotionToSend = "Benötige Hilfe";
                    break;
                case "HURRY":
                    logo = R.drawable.ic_warning_full;
                    headlineColor = R.color.colorHurry;
                    emotionToSend = "Dringend";
                    break;
                case "CRITICISM":
                    logo = R.drawable.ic_warning_round_full;
                    headlineColor = R.color.colorCriticism;
                    emotionToSend = "Kritik";
                    break;
                case "INCOMPREHENSION":
                    logo = R.drawable.ic_bold_round_full;
                    headlineColor = R.color.colorIncomprehension;
                    emotionToSend ="Unverständnis";
                    break;
            }
        }

        LinearLayout layoutBase = new LinearLayout(context);
        LinearLayout layoutWeight1 = new LinearLayout(context);
        LinearLayout layoutWeight2 = new LinearLayout(context);

        LinearLayout.LayoutParams paramsLayoutBase = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsLayoutBase.setMargins(0,30,0,0);
        layoutBase.setLayoutParams(paramsLayoutBase);
        layoutBase.setFocusable(true);
        layoutBase.setFocusableInTouchMode(true);

        LinearLayout.LayoutParams paramsWeightBig = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT, 60f);
        LinearLayout.LayoutParams paramsWeightSmall = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT, 40f);

        layoutWeight1.setGravity(Gravity.START);
        layoutWeight2.setGravity(Gravity.END);

        LinearLayout layoutBubble = new LinearLayout(context);

        layoutBubble.setOrientation(LinearLayout.VERTICAL);
        layoutBubble.setPadding(25,20,25,20);
        layoutBubble.setBackground(ContextCompat.getDrawable(context, R.drawable.layout_speech_bubble));

        TextView viewHeader;
        TextView viewSubject;

        ImageView viewImage;

        LinearLayout innerlayout;

        if(project_b) {
            viewHeader = new TextView(context);
            viewHeader.setTypeface(null,Typeface.ITALIC);
            LinearLayout.LayoutParams parameter = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            parameter.setMargins(0,20,0,0);
            viewHeader.setLayoutParams(parameter);
            viewHeader.setText(sender);

            layoutBubble.addView(viewHeader);
        }

        if(template_b){

            innerlayout = new LinearLayout(context);
            LinearLayout.LayoutParams paramsLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            paramsLayout.setMargins(0,20,0,0);
            innerlayout.setLayoutParams(paramsLayout);
            innerlayout.setOrientation(LinearLayout.HORIZONTAL);

            viewImage = new ImageView(context);
            LinearLayout.LayoutParams paramsImage = new LinearLayout.LayoutParams(25, 25);
            paramsImage.setMargins(0,0,10,0);
            viewImage.setLayoutParams(paramsImage);
            viewImage.setColorFilter(ContextCompat.getColor(context, headlineColor));
            viewImage.setImageDrawable(ContextCompat.getDrawable(context,logo));

            viewSubject = new TextView(context);
            viewSubject.setTypeface(null,Typeface.BOLD);
            viewSubject.setTextColor(ContextCompat.getColor(context, headlineColor));
            viewSubject.setText(emotionToSend);

            innerlayout.addView(viewImage);
            innerlayout.addView(viewSubject);

            layoutBubble.addView(innerlayout);
        }

        TextView viewContent = new TextView(context);
        viewContent.setText(messageToSend);
        LinearLayout.LayoutParams paramsView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsView.setMargins(0,20,0,0);
        viewContent.setLayoutParams(paramsView);

        layoutBubble.addView(viewContent);

        if(left_b) {
            layoutWeight1.setLayoutParams(paramsWeightBig);
            layoutWeight2.setLayoutParams(paramsWeightSmall);
            layoutWeight1.addView(layoutBubble);
        } else {
            layoutWeight1.setLayoutParams(paramsWeightSmall);
            layoutWeight2.setLayoutParams(paramsWeightBig);
            layoutWeight2.addView(layoutBubble);
        }

        layoutBase.addView(layoutWeight1);
        layoutBase.addView(layoutWeight2);

        view = layoutBase;
    }

    class Sender{
        final String name;
        final String id;

        Sender(String name, String id){
            this.name = name;
            this.id = id;
        }
    }
}
