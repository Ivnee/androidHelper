package com.geek.infoandroid.android.Level2.les4.OkHttp;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.geek.infoandroid.R;
//он сразу конвертирует в JSON если я правильно понял
public class ActivityOkHttp extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);//определяем наш лейаут
        webView = findViewById(R.id.okhttp_web_view);//находим веб вью
        OkHttpRequest okHttpRequest = new OkHttpRequest(new OkHttpRequest.CompleteRequestInterface() {//создадим наш класс запроса у которого реализуем коллбек метод(на подобие new Thread)
            @Override
            public void onCompleted(String content) {
                webView.loadData(content, "text/html; charset=utf-8", "utf-8");//открываем нашу страничку в веб вью
            }

            @Override
            public void onError(int errorCode) {
                Toast.makeText(getBaseContext(), "Error happened, code: " + errorCode, Toast.LENGTH_SHORT)
                        .show();
            }
        });
        okHttpRequest.run("http://www.google.com");//передаем ссылку для запуска запрос
    }
}
