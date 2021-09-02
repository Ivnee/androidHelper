package com.geek.infoandroid.Level1.les2;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.geek.infoandroid.databinding.ActivityMainBinding;

public class ViewBinding1 extends AppCompatActivity {
    /*
    1)вставляем в гредл модуль в android{} ->     viewBinding {enabled = true}
* */
    //ПРО БИНДиНГ УРОК 6 2:35 - 40 примерно,все четко,и как во фрагменте юзать и как в мейн активити
    ActivityMainBinding binding;//создаем биндинг
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));//подключаем наш биндинг
        setContentView(binding.getRoot());//и переддаем сюда наш биндинг
        binding.menuButton.setText("213");//через биндинг вью есть доступ к нашим элементам,можно сразу делать сет текст сет колор и тд без файнд вью бай айди
        binding.firstTextView.setText("228");//достаются походу только активити мэйн хмл


    }
}
