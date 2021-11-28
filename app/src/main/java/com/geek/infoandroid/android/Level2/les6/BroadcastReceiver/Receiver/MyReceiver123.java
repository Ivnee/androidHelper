package com.geek.infoandroid.android.Level2.les6.BroadcastReceiver.Receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.geek.infoandroid.R;

public class MyReceiver123 extends BroadcastReceiver {
    private int messageId;

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("TAG KEY MSG");//получаем наше сообщение
        if (message == null) {
            message = "";
        }
        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();//выводим наше сообщение(можно как угодно обрабатывать сообщения которые мы получаем)
//создаем оповещение
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "2")//создаем оповещение через билдер
                .setSmallIcon(R.mipmap.ic_launcher)//сетим иконку
                .setContentTitle("Broadcast Receiver")//сетим титул
                .setContentText(message);//и вставляем полученное сообщение
        //и запускаем наше оповещение
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(messageId++, builder.build());

    }
}
