package com.geek.infoandroid.Kotlin.les7RetrofitOkHttpGlide.retrofitCoroutines

import com.geek.infoandroid.Level2.les4.RetrofitInfo.RetrofitModel.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class RetrofitRepository {
//это можно в даггере реализовать и пропихнуть в зависимость в репозиторий
    fun createForDaggerApiResponse():MyApi{
        val retrofit = Retrofit.Builder().baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(MyApi::class.java)
    }
    fun getWeather(){
        val myApi = createForDaggerApiResponse()
        myApi.getWeather("dmitrov","myKey").enqueue(object :
            Callback<ApiModel> {
            override fun onResponse(
                call: Call<ApiModel>,
                response: Response<ApiModel>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        val weather = Weather(city,it.main.temp)
                        print(weather.toString())
                        //callback.invoke(Success(weather))
                    }
                }else{
                    print(t.stackTrace)
                    //callback.invoke(Error(Exception()))
                }
            }

            override fun onFailure(call: Call<ApiModel>, t: Throwable) {
                print(t.stackTrace)
            //callback.invoke(Error(t))
            }
        })
    }
}