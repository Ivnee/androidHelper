package com.geek.lection1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {// активити которая поддерживает тулбар,Если от обычной Activity тулбара не будет
    TextView textView ;
    Switch switch1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//подключает лейаут
        // для макета
        //imageView  android:scaleType="centreInside"сохраняет пропорции не растягивает , "centreCrop если больше ,обрезается
        //src добавляет картинку
        initViews();
        viewsCommands();
        xmlCommands();

    }


    private void viewsCommands() {
        int color = ContextCompat.getColor(getBaseContext(),R.color.colorAccent);//как правильно брать цвет setTextColor
        textView.setVisibility(View.GONE);//полностью убирает с экрана
        textView.setVisibility(View.VISIBLE);//видимый
        textView.setVisibility(View.INVISIBLE);//невидимый,но контейнер занимает место
        boolean orientation = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;//проверяем ориентацию экрана

    }
    private void xmlCommands() {
        //textView android:lines="1" количество линий
        //android:foreground="?android:attr/selectableItemBackground"//волна при нажатии

        //switch1 checked="false" "true"по умолчанию включен или нет
        //RecyclerView

    }

    private void initViews() {
        textView.findViewById(R.id.textView);
        switch1.findViewById(R.id.switch1);
    }

}
