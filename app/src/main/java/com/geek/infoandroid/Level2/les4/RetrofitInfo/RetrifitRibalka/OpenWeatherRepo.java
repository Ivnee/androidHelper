package com.geek.infoandroid.Level2.les4.RetrofitInfo.RetrifitRibalka;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenWeatherRepo {//создаем класс через который будем обращаться ко всем данным
    private static OpenWeatherRepo instance = null;
    private IOpenWeather API;

   /* private OpenWeatherRepo() {//конструктор который сразу инициализирует наш интерфейс через ретрофит
        API = createAdapter();
    }*/

    public static OpenWeatherRepo getInstance() {
        if(instance == null) {
            instance = new OpenWeatherRepo();//статический емтод инициализации(если нет нашего объекта то создаем его)
        }
        return instance;//возвращаем наш объект
    }

    public IOpenWeather getAPI() {
        return API;
    }

/*    private IOpenWeather createAdapter() {//у ретрофита создаем интерфейс запросов и возвращаем его из этого метода
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")//базовая ссылка
                .addConverterFactory(GsonConverterFactory.create())//способ конвертации данных
                .build();//собрать

        return retrofit.create(IOpenWeather.class);//возвращаем наш запрос(в дальнейшем вставляем в метод интерфейса loadWeather все нужные параметры city key metric
    }*/
}
