package com.geek.infoandroid.android.Level2.les6.BroadcastReceiver.Receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.geek.infoandroid.R;

public class AirplaneReceiver extends BroadcastReceiver {//обозначанм что это наш ресивер
    private int messageId = 0;
    @Override
    public void onReceive(Context context, Intent intent) {//метод вызывается когда происходит событие на которое мы подписались

        String message = intent.getStringExtra("tag key");
        if (message == null){
            message = "";
        }
        // создать нотификацию
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "2")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Broadcast Receiver")
                .setContentText(message);
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(messageId++, builder.build());
    }
}
