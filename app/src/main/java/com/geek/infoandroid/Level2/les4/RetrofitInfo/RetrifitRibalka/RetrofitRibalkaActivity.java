package com.geek.infoandroid.Level2.les4.RetrofitInfo.RetrifitRibalka;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.geek.infoandroid.Level2.les4.RetrofitInfo.RetrifitRibalka.ModelRibalka.WeatherRequestRestModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitRibalkaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void updateWeatherData(final String city) {
        OpenWeatherRepo.getInstance().getAPI().loadWeather(city + ",ru",    //берем класс,достаем из него адаптер из нашего интерфейса и у интерфейсса вызываем лоад везер
                "762ee61f52313fbd10a4eb54ae4d4de2", "metric")
                .enqueue(new Callback<WeatherRequestRestModel>() {          //выполняем коллбэк в потоке
                    @Override
                    public void onResponse(@NonNull Call<WeatherRequestRestModel> call,
                                           @NonNull Response<WeatherRequestRestModel> response) {   //получаем тот самый интерфейс
                        if (response.body() != null && response.isSuccessful()) {                   //у которого берем боди(боди это и есть наша модель данных)
                            WeatherRequestRestModel model = response.body(); //получили модель
                            model.name//имя города
                            model.sys.country//страна
                            model.main.temp//температура и так далее парсим данные
                        } else {
                            //Похоже, код у нас не в диапазоне [200..300) и случилась ошибка
                            //обрабатываем ее
                        }

                        //сбой при интернет подключении
                        @Override
                        public void onFailure (Call < WeatherRequestRestModel > call, Throwable t){
                        }
                    });
                }
    }
