package com.geek.infoandroid.android.IntentSingTonLifeCycle;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Intents2 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent getIntent = getIntent();//получаем интент,которым запустил эту активити
        String textFromIntent = getIntent.getStringExtra("ключ");//достаем данные ,которые передали в данную страницу
        Bundle bundle = getIntent.getExtras();//или получаем все данный которые были в интенте в виде бандла
        Intent resultIntent = new Intent();//интент для возврата данных
        resultIntent.putExtra("ключ2", "сами данные");//кладем результат
        setResult(RESULT_OK,resultIntent);//отправляем результат обратно в нашу активити
        setResult(RESULT_CANCELED);//отменяем результат
        finish();//закрывает активити ,которую открыли интентом
    }
}
