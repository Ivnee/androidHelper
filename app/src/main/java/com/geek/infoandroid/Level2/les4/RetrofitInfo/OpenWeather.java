package com.geek.infoandroid.Level2.les4.RetrofitInfo;

import com.geek.infoandroid.Level2.les4.RetrofitInfo.RetrofitModel.WeatherRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeather {
    @GET("data/2.5/weather")//указываем недостающую часть адреса
    //в угловых скобках указываем класс в котором содержатся все объекты для размещения данных
    Call<WeatherRequest> loadWeather(@Query("q") String cityCountry,//какой будет подставлться айди ,"q"=должно писаться так же как и в самой ссылке
                                     @Query("appid")String keyApi);//какой будет подставляться ключ
    //можно добавить сколько угодно Query и они будут добавляться к ссылке через &(units=metric)
//если используем @Path
   /* @GET("data/2.5/weather/{userid}/{tovar}")//Path подменяяет то что написано в {...}
    Call<WeatherRequest> zagrujaemPogodu(@Path("userid") String id,//подставится вместо userID наш userID который мы дадим
                                     @Path("tovar") String tovarKey);
//если используем @Body и к нему соответственно не @GET а @POST
    @POST("data/2.5/weather/{userid}/{tovar}")//Path подменяяет то что написано в {...}
    Call<WeatherRequest> zagrujaemPogodu(@Body String name,//боди отправляет объект на сервер ,который помечен @SerrializeName и @Expose из <WeatherRequest>
                                     @Body String temperature);
*/}
