package com.geek.infoandroid.Internet8;

import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.geek.infoandroid.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class InternetBrowser extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadPage();
    }

    private void loadPage() {
        final WebView webView = findViewById(R.id.web_view);
        //webView.loadUrl("https://geekbrains.ru/");//открыть страничку через веб вью

//Создаем соединение в ручную
        final Handler handler = new Handler();//класс который позволяет сохранть ссылку на тот поток в котором он был создан
        new Thread(new Runnable() {//создаем новый поток для того чтоб соединение не грузилось в UI потоке ,чтоб не висла вся система
            @Override
            public void run() {
                HttpsURLConnection urlConnection = null;//создаем урл коннекшн
                URL url = null;
                try {
                    url = new URL("https://google.com/");//создаем ссылку
                    urlConnection = (HttpsURLConnection)url.openConnection();//открываем соединение ,создаем запрос
                    urlConnection.setRequestMethod("GET");//устанавливаем метод получения данных для данной ссылки
                    urlConnection.setReadTimeout(10000);//установка времени ожидания ответа(если за 10 сек не выполняется выводим ошибку)
                    BufferedReader in =  new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));//создаем баффер ридер который будет получать поток из нашего соединения

                    StringBuilder sb = new StringBuilder();//создаем стринг билдер для того чтоб собрать все полученные данные
                    String line;//создаем строку чтоб сетить постночно все данные из буффер ридер
                    while((line = in.readLine()) != null){//цикл в котором осуществляем всю работу
                        sb.append(line).append("\n");//добавляем полученную строку и делаем новую строку,потому как читаем построчно
                    }
                    in.close();//после того как вышли из цыкла закрываем поток
                    final String result =sb.toString();//сетим результать из стринг билдера в переменную резалт и билдер переводим в стоку
                    handler.post(new Runnable() {//Возвращаемся через хендлер в поток ui и сетим полученные данные нашим запросам в вспомогательном потоке
                        @Override
                        public void run() {
                            webView.loadData(result,"text/html; charset=utf-8","utf-8");//открываем страничуц в р учную
                        }
                    });


                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    urlConnection.disconnect();//отключаемся от нашего соединения
                }
            }
        }).start();
    }
}
