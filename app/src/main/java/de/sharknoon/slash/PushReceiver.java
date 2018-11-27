package de.sharknoon.slash;

import de.sharknoon.slash.Activties.LoginActivity;
import me.pushy.sdk.Pushy;
import android.content.Intent;
import android.graphics.Color;
import android.content.Context;
import android.app.PendingIntent;
import android.media.RingtoneManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.support.v4.app.NotificationCompat;

public class PushReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String type = "NO_TYPE_DEFINED";
        String from = "NO_USER_DEFINED";
        String[] content = {};

        String subject = "NO_SUBJECT_DEFINED";
        String message = "NO_MESSAGE_DEFINED";
        String category = "NO_CATEGORY_DEFINED";

        String status = "NO_STATUS_DEFINED";

        if(intent.getStringExtra("type") != null){
            type = intent.getStringExtra("type");
        }

        if(intent.getStringExtra("from") != null){
            from = intent.getStringExtra("from");
        }

        if(intent.getStringArrayExtra("content") != null){
            content = intent.getStringArrayExtra("content");

            if(intent.getStringExtra("subject") != null){
                subject = intent.getStringExtra("subject");
            }

            if(intent.getStringExtra("message") != null){
                message = intent.getStringExtra("message");
            }

            if(intent.getStringExtra("category") != null){
                category = intent.getStringExtra("category");
            }
        } else {
            message = "NO_CONTENT_DEFINED";
        }

        if(intent.getStringExtra("status") != null){
            status = intent.getStringExtra("status");
        }

        // Prepare a notification with vibration, sound and lights
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setAutoCancel(true)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(from)
                .setContentText(message)
                .setLights(Color.RED, 1000, 1000)
                .setVibrate(new long[]{0, 400, 250, 400})
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, LoginActivity.class), PendingIntent.FLAG_UPDATE_CURRENT));

        // Automatically configure a Notification Channel for devices running Android O+
        Pushy.setNotificationChannel(builder, context);

        // Get an instance of the NotificationManager service
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        // Build the notification and display it
        notificationManager.notify(1, builder.build());
    }
}
