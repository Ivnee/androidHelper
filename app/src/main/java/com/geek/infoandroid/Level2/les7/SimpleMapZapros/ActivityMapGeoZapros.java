package com.geek.infoandroid.Level2.les7.SimpleMapZapros;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.geek.infoandroid.R;

public class ActivityMapGeoZapros extends AppCompatActivity {//в фрагментах все тоже саоме

    Button button1;
    Button button2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        button1 = findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String geo ="geo:55.749792,37.632495";//сохраняем широту и долготу какой-то точки
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geo));//создаем интент на открытие точки на карте
////можно без проверки на приложение ,это можно вырезать вообще,тогда смартфон сам предложит нам выбрать приложение
/////////////////////////////////////////////////
                PackageManager packageManager = getPackageManager();//создаем пекедж менеджер для работы с аппами
                if(kykyPackageInstalled("com.google.android.apps.maps",packageManager)) {//есть ли это приложение на телефоне
                    intent.setPackage("com.google.android.apps.maps");//устанавливаем приложение,которое откроет этот интент
                    //если сделать сет пекедж без проверки и то что мы сетим не установлено,то наш апп крашнется
                }
//////////////////////////////////////
                startActivity(intent);//запустить карту с этой точкой
            }
        });
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String geo = "geo:55.749792,37.632495?z=3";//указать z это отдаленная камера
                String geo2 = "geo:55.749792,37.632495?z=15";//дефолтное положение камеры
                String geo3 = "geo:0,0?z=10&q=Москва Красная площадь 3";//запрос через q это запрос как в гугл мапс,просто текстом
                String geo4 = "geo:0,0?&q=Кафе рядом";//покажет все кафешки поблизости
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(geo));
                startActivity(intent);

            }
        });

    }



    private boolean kykyPackageInstalled(String pachageName,PackageManager packageManager){
        try {
            packageManager.getPackageInfo(pachageName,0);//получаем информацию о приложении
            return true;//если оно есть ,то возвращаем тру
        } catch (PackageManager.NameNotFoundException e) {
            return false;//если не найдено приложение,которое мы ищем,возвращаем фолс
        }
    }

}
