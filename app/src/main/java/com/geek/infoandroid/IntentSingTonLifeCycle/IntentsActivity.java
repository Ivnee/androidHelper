package com.geek.infoandroid.IntentSingTonLifeCycle;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class IntentsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(getApplicationContext(), Intents2.class);//указываем на какую страницу хотим перейти
        intent.putExtra("ключ","14.06.1995");//кладем данные в интент

        Uri uri = Uri.parse("https://www.google.com/");//класс который сохраняет ссылки(сохраняет путь перехода куда - либо)
        Intent intentBrowser = new Intent(Intent.ACTION_VIEW, uri);//ACTION_VIEW - запустить что-либо на просмотр( этот интент запускает веб страницу)
        Intent intentChooser = Intent.createChooser(intentBrowser,"открой меня");//если не будет приложения,которое может открыть ссылку, то наш апп не умрет
        //по этому типу определяет какое приложение должно обрабатывать нашу ссылку(не обязательно указывать тип,используется для уточнения)
        intentBrowser.setType("image/jpeg");//указываем тип того,что мы хотим открыть по ссылке ури через интент, ссылка с видами типов https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_MIME-%D1%82%D0%B8%D0%BF%D0%BE%D0%B2

        Uri appUri = Uri.parse("scheme://host");//ури для запуска приложения 2
        Intent openAppIntent = new Intent ( Intent.ACTION_VIEW, appUri);//интент для открытия аппа2(указать в манифесте аппа2 фильтр на прием этого ури,чтоб оно определяло данный интент)
        Intent asd = Intent.createChooser(openAppIntent,"select browser");//вызывает окошко выбора приложения ,которое будет открывать активити,чтоб открыть это окно, в старт активити даем asd,титл это то что будет наверху этого окошка написано

        //а есть ли приложение которое должно словить этот ивент?????
        ActivityInfo activityInfo = openAppIntent.resolveActivityInfo(getPackageManager(),openAppIntent.getFlags());//или
        ComponentName cn =openAppIntent.resolveActivity(getPackageManager());//тоже проверит есть ли приложение по этому адресу
        if (activityInfo != null) startActivity(openAppIntent);//если это приложение вообще есть,то запускай


        startActivity(intent);//осуществляем переход
        startActivityForResult(intent,100);//запускаем активити на получение результата(100 - код ,по которому заберем данные потом)

        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);//интент для отправки смс сообщений
        smsIntent.setData(Uri.parse("smsto:+79776599820"));//указываем кому будем отправлять смс
        smsIntent.setData(Uri.parse("tel:" + "899999999999"));//сразу набирает номер
        smsIntent.putExtra("smsKey","сообщение");//кладем сообщение в интент(запускаем через startActivity())


        //про ури
        Uri appUri2 = Uri.parse("scheme://host");//ури для запуска приложения 2
        Uri uri2 = Uri.parse("https://www.google.com/");//класс который сохраняет ссылки(сохраняет путь перехода куда - либо)
        uri2.getScheme();//получить схему,тоесть http https scheme и тд, что это за ури и для чего предназначен
        uri2.getPort();//вернет порт нашей строки,последнее значение как в вк типа id вроде
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {//получаем результат из интент2(вызывается при возврате на эту страницу)
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){//если реквест код == 100 (код по которому запскаем startActivityForResult) то выполняем
            if(resultCode == RESULT_OK){//если отправленный результат ок, то выполняем
                String str = data.getStringExtra("ключ2");//получаем результат ,который пеередавали
            }
        }
    }
}
