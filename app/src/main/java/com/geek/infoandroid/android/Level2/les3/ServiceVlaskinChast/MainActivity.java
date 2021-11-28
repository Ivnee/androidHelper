package com.geek.infoandroid.android.Level2.les3.ServiceVlaskinChast;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private boolean isConnectBound;
    private BoundService myBoundService;
    private BoundService.MyServiceBinder myServiceBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStartBindServise();

    }

    //создаем сервис коннекшон для обработки событий подключения и отключения от сервиса
    private ServiceConnection boundServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {//вызывается при соединении с сервивсом(в этом методе нам приходит наш сервис)
            myServiceBinder = (BoundService.MyServiceBinder) service;//получаем сервис ,к которому подвязались
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {//при отсоединении от сервиса вызывается этот колбэк
            isConnectBound = false;
            myBoundService = null;
        }
    };


    private void mStartBindServise() {
        //создаем интент чтобы понимать к чему мы хотим привязаться
        Intent intent = new Intent(MainActivity.this,BoundService.class);
        bindService(intent,boundServiceConnection,BIND_AUTO_CREATE);//привязываемся(интент,подключ отключ, флаг автосоздание соединения)
        myServiceBinder.gettt();//получаем данные из сервиса через вложенный класс

    }
}