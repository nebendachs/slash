package de.sharknoon.slash;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import de.sharknoon.slash.Activties.LoginActivity;
import me.pushy.sdk.Pushy;

public class PushReceiver extends BroadcastReceiver {

    private static int counter = 0;

    @Override
    public void onReceive(Context context, Intent intent) {

        String status = "NO_STATUS_DEFINED";
        String type = "NO_TYPE_DEFINED";
        String from = "NO_USER_DEFINED";
        String content = "NO_CONTENT_DEFINED";

        String subject = "NO_SUBJECT_DEFINED";
        String message = "NO_MESSAGE_DEFINED";
        String category = "NO_CATEGORY_DEFINED";

        if(intent.getStringExtra("status") != null){
            status = intent.getStringExtra("status");
        }

        if(intent.getStringExtra("type") != null){
            type = intent.getStringExtra("type");
        }

        if(intent.getStringExtra("from") != null){
            from = intent.getStringExtra("from");
        }

        if(intent.getStringExtra("content") != null) {
            content = intent.getStringExtra("content");
            if(type.equals("EMOTION")) {
                JsonParser parser = new JsonParser();
                JsonObject jsonObject = parser.parse(content).getAsJsonObject();
                subject = jsonObject.get("subject").getAsString();
                message = jsonObject.get("message").getAsString();
                category = jsonObject.get("category").getAsString();
            }
        }

        int color = R.color.colorBlack;
        int logo = R.drawable.logo2;

        if(status.equals("BAD_PROJECT_SENTIMENT")) {
            message = String.format("Watch out! The mood in project %s has turned sour.", from);
            from = "Slash Bot";
            logo = R.drawable.ic_info_full;
            color = R.color.colorBadSentiment;
        } else {
            switch (type) {
                case "EMOTION":
                    message = subject + ":\n" + message;
                    break;
                case "TEXT":
                    message = content;
                    break;
                case "IMAGE":
                    message = "Image";
                    break;
                default:
                    message = "New message";
            }

            switch (category) {
                case "SUCCESS":
                    logo = R.drawable.ic_checkmark_full;
                    color = R.color.colorSuccess;
                    break;
                case "OK":
                    logo = R.drawable.ic_thumps_up_full;
                    color = R.color.colorOk;
                    break;
                case "INFO":
                    logo = R.drawable.ic_info_full;
                    color = R.color.colorInfo;
                    break;
                case "QUESTION":
                    logo = R.drawable.ic_help_full;
                    color = R.color.colorQuestion;
                    break;
                case "HELP":
                    logo = R.drawable.ic_man_full;
                    color = R.color.colorHelp;
                    break;
                case "HURRY":
                    logo = R.drawable.ic_warning_full;
                    color = R.color.colorHurry;
                    break;
                case "CRITICISM":
                    logo = R.drawable.ic_warning_round_full;
                    color = R.color.colorCriticism;
                    break;
                case "INCOMPREHENSION":
                    logo = R.drawable.ic_bold_round_full;
                    color = R.color.colorIncomprehension;
                    break;
            }
        }

        // Prepare a notification with vibration, sound and lights
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"pushy")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setAutoCancel(true)
                .setColorized(true)
                .setColor(ContextCompat.getColor(context, color))
                .setColorized(true)
                .setSmallIcon(logo)
                .setContentTitle(from)
                .setContentText(message)
                .setLights(Color.RED, 1000, 1000)
                .setVibrate(new long[]{0, 400, 250, 400})
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(PendingIntent.getActivity(context, counter, new Intent(context, LoginActivity.class), PendingIntent.FLAG_UPDATE_CURRENT));

        // Automatically configure a Notification Channel for devices running Android O+
        Pushy.setNotificationChannel(builder, context);

        // Get an instance of the NotificationManager service
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Build the notification and display it
        notificationManager.notify(counter, builder.build());

        counter++;
    }
}
