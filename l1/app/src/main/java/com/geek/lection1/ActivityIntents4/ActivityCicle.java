package com.geek.lection1.ActivityIntents4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.geek.lection1.R;

public class ActivityCicle extends AppCompatActivity {
//1  только  при создании активити вызывается
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
    }
//2   вызывается до того как на экране что-то покажется
    @Override
    protected void onStart() {
        super.onStart();
    }
    //3 после того как на экране что-то покажется

    @Override
    protected void onResume() {
        super.onResume();
    }
    //4 вызывается когда на экрене что-то есть,но оно сейчас исчезнет или что-то не полностью перекрыло экран
    @Override
    protected void onPause() {
        super.onPause();
    }
    //5   когда на экране уже ничего нет,либо свернули либо перешли в другую активити
    @Override
    protected void onStop() {
        super.onStop();
    }
    //6   или при повороте экрана или когда наше активити полностью закрывается
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        String text = savedInstanceState.getString("key");//получить значение из бандла
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("key","наша строка");//положить значение в бандл
        super.onSaveInstanceState(outState);
    }

}
