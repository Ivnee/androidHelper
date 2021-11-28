package com.geek.infoandroid.android.Level2.les3.Services;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {//1)интент,который мы передаем в команду StartService2)дает понять что это повторная попытка вызова onStartCommand 3)счетчик вызовов онСтартКоманд,первый вызов дает 1 (если убить сервис счетчик обнулится)
        //какой-то код для выполнения в new Thread(new Runnable{...}).start();
        int a = intent.getIntExtra("time",0);//получаем из интента то что передавали при старте сервиса
        int nomerServisa =startId;
        stopSelf();//сервис убьет сам себя(можно в любом потоке)(принимает в виде аргумента startID-номер сервиса)


        if(flags ==START_FLAG_RETRY){//проверяем ,если ретрай то запущен впервые наш сервис(уточнить)
            //код с которым работаем
        }
        boolean isStop = stopSelfResult(startId);//возвращает тру ,если вызов этого метода убил сервис,принимает на вход номер запущенного сервиса


        //фореграунд сервис
        startForeground(228,makeNotificationn("ForegroundService"));//обязательно в он старт онмманд вызываем этот метод для фореграунд сервиса

        //пендинг интент
        final PendingIntent pendingIntent = intent.getParcelableExtra("pendingIntent");//вытаскивыем пендинг интент из onStartCommand intent

        try {
            pendingIntent.send(0);//отправляем в активити реквест код 0,чтоб обозначить что работа началась
            //выполняем работу и потом уже отправляем реквест код 1(типа работа доделана,можно любую цифпу) и интент со всеми результатами и даннми
            Intent intent1 = new Intent().putExtra("int",200);//создаем интент с интом
            pendingIntent.send(MyService.this,1,intent);//отправляем данные в нашу активити,в которой был создан пендинг интент(контекст,реквест код,интент который вернем обратно)
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }

        //флаги
        return START_REDELIVER_INTENT;//сервис будет перезапущен,после того как был убит системой и получит все вызовы startService которые небыли завершены методом stopSelf(тоесть доделает ту работу которую не доделал)
        //return START_STICKY;//сервис будет перезапущет,после того как был убит системой
        //return START_NOT_STICKY;//сервис не будет перезапущен после того как был убит системой


    }

    @Nullable
    @Override//привязка(можно оставить ретерн нулл)
    public IBinder onBind(Intent intent) {
        return null;
    }
}
