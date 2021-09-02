package com.geek.infoandroid.Level3libraries.Patterns.MvcAndObserver;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.geek.infoandroid.R;

import java.util.Observable;
import java.util.Observer;

public class MVCActivity extends AppCompatActivity implements Observer, View.OnClickListener{//обсервер,класс который подписывается на обсервэйбл
    Button button1;
    Button button2;

    Model model;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvc_activity);
        button1 = findViewById(R.id.mvc_btn1);
        button2 = findViewById(R.id.mvc_btn2);
        model = new Model();
        model.addObserver(this);//метод джавы,подписывается на класс модели(для того ,чтобы подписаться нужно имплементить обсервер)
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mvc_btn1:
                model.setElementValueAtIndex(0);
                break;
            case R.id.mvc_btn2:
                model.setElementValueAtIndex(1);
                break;}


    }

    @Override
    public void update(Observable observable, Object o) {
        button1.setText(model.getElementValueAtIndex(0));//по индексу даем номер кнорки
        button2.setText(model.getElementValueAtIndex(1));//получаем из модели данные ,которые мы изменили в методе он клик
    }
}
