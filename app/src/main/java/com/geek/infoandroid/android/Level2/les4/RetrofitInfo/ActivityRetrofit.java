package com.geek.infoandroid.android.Level2.les4.RetrofitInfo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.geek.infoandroid.android.Level2.les4.RetrofitInfo.RetrofitModel.WeatherRequest;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//retrofit это по сути обертка от okHttp
//конвертирует и в GSON,Moshi,XML,Wire и в кучу других видов
public class ActivityRetrofit extends AppCompatActivity {
    OpenWeather openWeather;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRetrofit();
    }


    private void initRetrofit() {//создаем ретрофит
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")//именно базовый URL без параметров!!!!остальное укажем через интерфейс OpenWeather
                .addConverterFactory(GsonConverterFactory.create())//добавляем конвертор,то в каком формате будут получены данные
                .build();
        //подвязываем ретрофит к интерфейсу в котором делаем запрос
        openWeather = retrofit.create(OpenWeather.class);//получаем инстанс нашего ретрофита(создаем объекты ,с помощью которых делаются запросы)
    }
    private void requestRetrofit(String city, String key) throws IOException {
        openWeather.loadWeather(city, key)//сам Call запрос выролняется в другом потоке а респонс уже в гуи потоке
                .enqueue(new Callback<WeatherRequest>() {//вызываем метод колл бэк и в зависимости от того удачный ответ или нет вызывается соответствующий метод
                    @Override
                    public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {
                        float temperature = response.body().main.temp;//и потом сетим в нужную нам вьюху
                        System.out.println(temperature);//напишем результат
                    }

                    @Override
                    public void onFailure(Call<WeatherRequest> call, Throwable t) {
                        System.out.println("error");//какая-то ошибка с подключением

                    }
                });
        //или без коллбэков(нужно делать в другом потоке)

        Response<WeatherRequest> response =openWeather.loadWeather(city,key).execute();//создаем запрос
        WeatherRequest weatherRequest = response.body();//запрашываем все данные и вставляем их в модель
        String name = weatherRequest.name;//достаем из модели любые данные
    }

}
