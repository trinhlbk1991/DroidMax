package com.icetea09.droidmax.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.icetea09.droidmax.R;

public class NotificationUtils {

    public static void showNotification(Context mContext, String title, String message) {
        int icon = R.drawable.ic_launcher;
        showNotification(mContext, icon, null, title, message, null, 0, null);
    }

    public static void showNotification(Context mContext, int icon, Bitmap picture, String title, String message, Intent notificationIntent, int id, String tag) {
        try {
            NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(mContext)
                            .setSmallIcon(icon)
                            .setContentTitle(title)
                            .setContentText(message);
            mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);

            if (picture != null) {
                NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
                style.setBigContentTitle(title);
                style.setSummaryText(message);
                style.bigPicture(picture);
                mBuilder.setStyle(style);

            } else {
                Log.i("NotificationUtils", "inboxStyle");
                NotificationCompat.BigTextStyle inboxStyle = new NotificationCompat.BigTextStyle()
                        .bigText(message);
                mBuilder.setStyle(inboxStyle);
            }

            Notification notification = mBuilder.build();
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            // Play default notification sound
            notification.defaults |= Notification.DEFAULT_SOUND;
            // Vibrate if vibrate is enabled
            notification.defaults |= Notification.DEFAULT_VIBRATE;
            notificationManager.notify(tag, id, notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
