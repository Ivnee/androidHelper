package com.geek.infoandroid.android.Level1.les7;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class EditText extends AppCompatActivity {
    TextInputEditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editText.setError("123");//установить ошибку на эдиттекст

        Pattern proverka= Pattern.compile("^[A-Z][a-z]{2,}$");//паттерн для проверки правильности текста(в этом паттерне должно начинаться с большой буквы слово
        boolean is = proverka.matcher("С большой буквы").matches();//проверить ,соответствует ли текст требованиям паттерна

    }
}
