package com.geek.infoandroid.Kotlin.les5InternetAndPropertyKey

import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import java.net.URL
import javax.net.ssl.HttpsURLConnection
//запрос который мы сделали в репозитории для вью модели
class ResponseHttpsUrlConnection {
    private val handler = Handler(Looper.getMainLooper())
    private val gson = Gson()
    fun myResponse(){
        val url =
            URL("https://api.openweathermap.org/data/2.5/weather?q=London&appid=132fce3d69979894a33cf504082ed717")
        val connection = url.openConnection() as HttpsURLConnection
        try {
            with(connection) {
                requestMethod = "GET"
                val result =inputStream.bufferedReader()
                val response = gson.fromJson(
                    result,
                    OpenWeatherMapResponse::class.java
                )
                val weather = Weather(city,response.main.temp)
                handler.post{
                    callback(Success(weather))
                }
            }
        } catch (ex: Exception) {
            handler.post {
                callback(Error(ex))
            }
        } finally {
            connection.disconnect()
        }
    }
}
