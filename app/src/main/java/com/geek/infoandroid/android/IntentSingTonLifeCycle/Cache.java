package com.geek.infoandroid.android.IntentSingTonLifeCycle;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Cache extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getCacheDir();//получить директорию кэша, место где хранятся временные файлы
    }
}
