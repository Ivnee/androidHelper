package com.geek.infoandroid.android.Level2.les3.ServiceVlaskinChast;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class BoundService extends Service {
    //создаем IBinder для связи активити с сервисом
    private final IBinder binder = new MyServiceBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    private int getInt(){
        return 20;
    }

    class MyServiceBinder extends Binder{//класс для связи между клиентом и сервисом
        BoundService getService() { return BoundService.this; }//возвращаем текущий серис
        int gettt(){
            return getService().getInt();}
    }
}
