package com.geek.lection1.ActivityIntents4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.geek.lection1.R;

public class Intents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents);
        pereytiNaDruguyuStanicu();
    }
//для получения результата из вызванных активити,создается в активити из которыой запускаем другие экраны
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123 ){//пришел код который мы отправляли
            if(resultCode == RESULT_OK&& data != null){//резалт код пришел с ок и данные есть в data
                System.out.println(data.getStringExtra("keyBack"));//получаем данные из активити 2
                //если резалт ок можем писаать сюда любой код ,если например нажали кнопку подтвердить то делаем следующие действия
            }
        }else{
            System.out.println("результат канцел");
            //если резалт канцел то тоже что-то делаем
        }

    }

    private void pereytiNaDruguyuStanicu() {
        //переход на активити
        Intent intent = new Intent(this,ActivityCicle.class);//перейти на активити 2
        intent.putExtra("key","любые данные для передачи");//вставляем данные
        startActivity(intent);//старт активити 2
        startActivityForResult(intent,123);//запускаем активити 2 для получения результата из него ,
        // указываем код для проверки возвращаемых данных

        //В активити 2
        Intent intentGetInfo = getIntent();
        String takeText = intentGetInfo.getStringExtra("key");// получаем данные которые передавали из активити 1
        finish();//закроет текущий экран,активити 2

        Intent intentGetBack = new Intent();
        intentGetBack.putExtra("keyBack","возвращаемые данные");//роложить данные для возврата в активити 1
        setResult(RESULT_OK,intentGetBack);//результат текущего экрана, для возвращения активити 1(данные получаем в методе onActivityResult)
        setResult(RESULT_CANCELED);//отменили отправку результата
/////////////
        //открывает страничку браузера
        String text = "www.google.com";//сохраняем ссылку на сайт
        Uri uri = Uri.parse(text);//парсим в формат ссылок(ури)
        Intent startInternetIntent = new Intent(Intent.ACTION_VIEW,uri);//экшн вью ( запустить что либо на просмотр), ури(просмотр через браузер)
        startActivity(startInternetIntent);//запускаем интернет страничку
///////////////
        //отправляем смс через интент
        String smsText = "текс сообщения для отправки";
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        smsIntent.setData(Uri.parse("smsto:+79776599820"));
        smsIntent.putExtra("keyForSms",smsText);
        startActivity(smsIntent);
//////////
        //запуск приложения 2 из приложения 1
        Uri appUri = Uri.parse("example://intent");//ключ интента для отклика(открывает приложение 2).у кого такой ключь тот и откроется
        Intent runAppIntent = new Intent(Intent.ACTION_VIEW,appUri);
        runAppIntent.putExtra("keyFor2App","кладем инфу для приложения 2");
        startActivity(runAppIntent);

        //приложение 2
        //манифест
        /*
            <intent-filter>
                <action android:name="android.intent.action.VIEW"/> активити является вьюхой
                <category android:name="android.intent.category.DEFAULT"/> дефолтная для запуска
                <category android:name="android.intent.category.BROWSABLE"/> она является просматриваемой(принимает на вход схему
                <data android:scheme="example"// запускает активити если приходит экземпл интент
        android:host="intent"/>
            </intent-filter>*/
        Intent intentGetOtherAppInfo = getIntent();//достаем интент который открыл наше приложение
        Bundle bundle = intentGetOtherAppInfo.getExtras();//бандл функция для передачи и хранения данных(передаем все данные интента в бандл)
        String str =bundle.getString("keyFor2App");//получаем данные из первого апа
    }
}
