package com.geek.infoandroid.android.Level2.les4.RetrofitInfo.RetrifitRibalka;


import com.geek.infoandroid.android.Level2.les4.RetrofitInfo.RetrifitRibalka.ModelRibalka.WeatherRequestRestModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOpenWeather {
    @GET("data/2.5/weather")//дописываем к основному запросу то что совпадает а дальше пишем то что нужно будет добавить в калл запросе
    Call<WeatherRequestRestModel> loadWeather(@Query("q") String city,//добавится к запросу q=и то что подставим
                                              @Query("appid") String keyApi,//&appid=и то что подставим
                                              @Query("units") String units);//&units=metric
}
