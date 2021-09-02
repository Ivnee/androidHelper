package com.geek.infoandroid.Kotlin.les7RetrofitOkHttpGlide.retrofitCoroutines

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {
    @GET("/data/2.5/weather")
    fun getWeather(@Query("q")cityName:String,@Query("appid")apiKey:String): Call<ApiModel>


    @GET("/data/2.5/weather")
    suspend fun getWeatherSuspend(
        @Query("q") cityName: String,
        @Query("appid") key: String
    ):ApiModel
}