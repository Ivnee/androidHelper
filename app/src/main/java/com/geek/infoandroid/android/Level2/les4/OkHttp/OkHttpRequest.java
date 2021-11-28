package com.geek.infoandroid.android.Level2.les4.OkHttp;

import android.os.Handler;
import android.os.Looper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpRequest {//вынесли логику в отдельный класс чтоб  не грузить активити
    CompleteRequestInterface listener;//коллбек интерфейс
    public OkHttpRequest(CompleteRequestInterface listener) {
        this.listener = listener;//полуаем листенер для колбэка
    }
    public void run(String url){
        OkHttpClient client = new OkHttpClient();//создаем клиент
        Request.Builder builder = new Request.Builder();//создаем билдер для запроса
        builder.url(url);//передаем билдеру ссылку для перехода
        Request request = builder.build();//генерим запрос и сохраняем его в реквест

        Call call= client.newCall(request);//создаем запрос , у окшттп клиента вызываем метод (новый запрос) в который передаем request запрос с нашей ссылуой,которую мы туда положили
             /*new Thread(() -> {
            try {
                Response response = call.execute();
                //...еще один запрос, берем данные из response
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();*///#2 вызов через execute(cинхронно)

        //в асинхронном потоке(вне гуи)
        call.enqueue(new Callback() {//определяем колбек,который выполнется , когда выполнется наш запрос(либо мы получим он респонс либо он фэйл
            final Handler handler = new Handler(Looper.myLooper());//создаем хендлер

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String answer = response.body().string();//получаем  текст нашего запроса JSON
                handler.post(new Runnable() {//постим результат в наш поток
                    @Override
                    public void run() {
                        listener.onCompleted(answer);

                    }
                });

            }
//вызывается при сбое сети
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

        });


    }
    public interface CompleteRequestInterface {
        void onCompleted(String content);
        void onError(int errorCode);
    }
}
