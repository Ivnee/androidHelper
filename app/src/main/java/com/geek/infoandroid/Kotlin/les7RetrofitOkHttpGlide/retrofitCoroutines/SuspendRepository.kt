package com.geek.infoandroid.Kotlin.les7RetrofitOkHttpGlide.retrofitCoroutines

import java.lang.Exception

class SuspendRepository {
    override suspend fun getWeatherSuspend(city: City): RepositoryResult<Weather> {
        try {
            val response = openWeatherMapApi.getWeatherSuspend(city.name,"key")
            return Success(Weather(city,response.main.temp))
        }catch (exc: Exception){
            return Error(exc)
        }
    }
}