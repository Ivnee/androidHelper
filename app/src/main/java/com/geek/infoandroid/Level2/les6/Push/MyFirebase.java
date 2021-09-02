package com.geek.infoandroid.Level2.les6.Push;

import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.geek.infoandroid.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebase extends FirebaseMessagingService {
//чекать учебное приложение для наглядност
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        //копия с учебного приложения
        Log.d("PushMessage", remoteMessage.getNotification().getBody());
        String title = remoteMessage.getNotification().getTitle();
        if (title == null){
            title = "Push Message";
        }
        String text = remoteMessage.getNotification().getBody();
        // создать нотификацию
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "2")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(text);
        NotificationManager notificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        //notificationManager.notify(messageId++, builder.build());
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        // Если надо посылать сообщения этому экземпляру приложения
        // или управлять подписками прилоения на стороне сервера,
        // сохраните этот токен в базе данных. отправьте этот токен вашему
        //КОРОЧЕ МЕТОД для того чтоб мы получили текущий токен нашего девайся,для того чтоб отправлять серверу(метод вызывается когда меняется токен устройства)
    }
}
