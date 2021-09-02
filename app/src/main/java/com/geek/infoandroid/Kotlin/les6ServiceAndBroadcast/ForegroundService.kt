package com.geek.infoandroid.Kotlin.les6ServiceAndBroadcast

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

//добавить сервис в манифест <service android:name=".Kotlin.les6ServiceAndBroadcast.ForegroundService"/>
//необходим пермишн <uses-permission android:name="android.permission.FOREGROUND_SERVICE"/>
class ForegroundService: Service() {
    companion object{
        const val CHANNEL_ID= "CHANNEL_ID"
    }
    override fun onBind(intent: Intent?): IBinder? =null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

//объявляем канал для нотификации
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(CHANNEL_ID,"channel",importance)
            val notificationManager =getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }

        //создаем нотификацию и запускаем наш фореграунд сервис
        val notification = NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("title").setContentText("text")
        startForeground(100500, notification.build())

        return START_NOT_STICKY
    }
}