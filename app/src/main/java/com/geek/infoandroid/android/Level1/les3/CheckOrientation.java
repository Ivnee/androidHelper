package com.geek.infoandroid.android.Level1.les3;

import android.content.res.Configuration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CheckOrientation extends AppCompatActivity {
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {//узнать об изменениях чего-либо в устройстве
        super.onConfigurationChanged(newConfig);
        int configuration = newConfig.orientation;//Узнать положение экрана если в манифесте указано что при повороте активити не пересоздается
    }
}
