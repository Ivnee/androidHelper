package com.geek.infoandroid.android.LiveDataViewModel.ViewModel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.geek.infoandroid.R;


public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);//запрашиваем у активити провайдеров в которых все вью модели, и указываем какая вью модель нам нужна

        MyViewModel viewModel1 = ViewModelProviders.of(this).get("viewModel1", MyViewModel.class);//если нам нужно сохранить несолько одинаковых вью моделей по ключу...
        MyViewModel viewModel2 = ViewModelProviders.of(this).get("viewModel2", MyViewModel.class);
        MyViewModel factory = ViewModelProviders.of(this,new ModelFactory(100)).get(MyViewModel.class);//вызываем вью модель в которую положили фактори(класс для передачи данных в конструктор вью модели)
        LiveData<String> data = viewModel.getData();//получаем лайфдату у этой вью модели через которую она будет давать нам ,хранящиеся в ней , данные
        data.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                System.out.println("обрабатываем данные " + s);
            }
        });
    }
}