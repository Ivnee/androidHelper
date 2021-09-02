package com.geek.infoandroid.Kotlin.les7RetrofitOkHttpGlide

import android.os.Handler
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.util.concurrent.ExecutorService

class WeatherOkHttpRepositoryImpl(
    private val gson: Gson,
    private val okHttpClient: OkHttpClient,
    private val mainThreadHandler: Handler
)  {
    override fun getWeather(
        executor: ExecutorService,
        city: City,
        callback: (result: RepositoryResult<Weather>) -> Unit
    ) {
        val request = Request.Builder().apply {
            addHeader("Header", "Header-value")//не обязательно,можем объявить хэдер
            url("https://api.openweathermap.org/data/2.5/weather?q=London&appid=132fce3d69979894a33cf504082ed717")
        }.build()
        val call = okHttpClient.newCall(request)
        //выполнить синхронно ,экзекют
        call.execute()
        //выполнить асинхронно ,в колбэке
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.invoke(Error(e))
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body?.byteStream()?.bufferedReader().let {

                        val response = gson.fromJson(
                            it,//buffered reader
                            OpenWeatherMapResponse::class.java//gson model
                        )
                        val weather = Weather(city, response.main.temp)

                        mainThreadHandler.post{
                            callback(Success(weather))
                        }
                    }
                } else {
                    print("fail")
                }
            }
        })
    }




}