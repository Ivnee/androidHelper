package com.geek.infoandroid.Level2.les7.Permissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class RequestPermissions extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)//разрешение на получение локации помоему
                != PackageManager.PERMISSION_GRANTED//пермишен грантед это значит подтвержденное разрешение
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)//тоже получение локации вроде
                != PackageManager.PERMISSION_GRANTED) {//если пермишены не подключены ,то

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, 100);//рапрашиваем следующие разрешения
        } else {
            //какой-то код ,если все пермишены подключены
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {//получаем результат по разрешениям
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100) {//проверяем код нашего разрешения,который мы указали
            boolean permissionsGranted = true;//если соответствует то тру
            for (int grantResult : grantResults) {//перебираем результаты всех пермишенов
                if (grantResult != PackageManager.PERMISSION_GRANTED) {//если пермишн не равен PERMISSION_GRANDED ,то ставим фолс
                    permissionsGranted = false;//ставим фолс
                    break;// и закрываем цикл
                }
            }
            if(permissionsGranted) recreate();//если разрешения все получены,то пересоздаем и возвращамся к повторному запросу пермишенов
        }
    }
}
