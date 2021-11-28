package com.geek.infoandroid.android.Level2.les6.BroadcastReceiver.Receiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.geek.infoandroid.R;

public class MainReceiver extends AppCompatActivity {
    AirplaneReceiver receiver = new AirplaneReceiver();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moxy_activity);
        registerReceiver(receiver, new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));//регистрируем ресивер и подписываем его на события включение выключения авиорежима
        unregisterReceiver(receiver);//отписываемся от ресивера (в методе он дестрой

        LocalBroadcastManager.getInstance(this).registerReceiver(new MyReceiver123(),new IntentFilter("ru.zel.receiver"));//регистрируем ресивер и подписываемся на его изменения(внутри приложения ,из вне не доступно)
        //LocalBroadcastManager.getInstance(this).unregisterReceiver();//отменить регистрацию ресивера в методе он дестрой
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent());//отправить локальное сообщение(в интент вставляем setAction("ru.zel.receiver"))


    }
}
