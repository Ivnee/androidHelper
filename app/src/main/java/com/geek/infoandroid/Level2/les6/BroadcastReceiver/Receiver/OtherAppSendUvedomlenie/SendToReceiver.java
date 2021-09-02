package com.geek.infoandroid.Level2.les6.BroadcastReceiver.Receiver.OtherAppSendUvedomlenie;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.geek.infoandroid.R;

public class SendToReceiver extends AppCompatActivity {
    //определяем в какой ресивер отправляется событие
    public static final String ACTION_SEND_MSG = ".Level2.les6.BroadcastReceiver.Receiver.MyReceiver123";//сюда пишем любой стринг
    public static final String TAG_INTENT = "send msg";
    public static final int FLAG_RECEIVER_INCLUDE_BACKGROUND = 0x01000000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);
        initBtnAndSend();

    }

    private void initBtnAndSend() {
        Button send = findViewById(R.id.menu_button);
        send.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")//аннотация чтоб игнорировать ошибку Flaga
            @Override
            public void onClick(View view) {
                String msg = "берем текст из едит текста";
                Intent intent = new Intent();
                intent.setAction(ACTION_SEND_MSG);//указываем где будем ловиться это сообщение
                intent.putExtra(TAG_INTENT,msg);//указываем само сообщение
                //он какая-то параша,не работает помоему
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);//с этим флагом сообщения будут приходить ,даже если апп не запущен
                intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);//этот флаг нужен чтоб приложение смогло принимать наше сообщение (???)судя по переводу ,наше сообщение кидает в самый перх
                intent.addFlags(0x01000000);//флаг ,чтоб приложение предлогалось даже если оно небыло открыто
                //работает только там где есть контекст
                sendBroadcast(intent);//отправляем всем бродкастам ,у который такой экшн указан в манифесте(  <receiver\n android:name="........MyReceiver123"
            }
        });
    }
}
