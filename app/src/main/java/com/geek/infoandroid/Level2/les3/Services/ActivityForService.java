package com.geek.infoandroid.Level2.les3.Services;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ActivityForService extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //в интент положили данные ,которые можем достать уже в сервисе(tag и любое значение)
        startService(new Intent(this,MyService.class).putExtra("time",10));//запустить наш сервис(метод onStartCommand)(интент можно передать с данными ,получим его в onStartCommand
        startService(new Intent("com.geek.infoandroid"));//путь по которому эта команда откроет сервис
        stopService(new Intent(this,MyService.class));//убить сервис
//запустить фореграунд сервис
       // Intent intent = new Intent(MainActivity.this,ForegroundService.class);
        //ContextCompat.startForegroundService(MainActivity.this,intent);
//отключить фореграунд сервис
        /*
        Intent intent = new Intent(MainActivity.this,ForegroundService.class);
        stopService(intent);
        */
        Intent intent = new Intent();//создаем просто пустой интент
        PendingIntent pi = createPendingResult(1,intent,0);//cоздаем пендинг интент и суем в него номер запроса и пустой интент
        Intent serviceIntent = new Intent(this,MyService.class).putExtra("pendingIntent",pi);//интент для вызова сервиса(кладем тег и пендинг интент
        startService(serviceIntent);//запускаем в сервисе этот интент

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {//для пендинг интента
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {//реквест код указывается в сервисе при отпревке пендинг интента .send
            int result = data.getIntExtra("int", 0);//получаем число из интента
        }
        }
}
