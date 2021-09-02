package com.geek.infoandroid.Kotlin.les3.dagger

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RepositoryModule {
//предоставляет реализацию репозитория RepositoryImpl
    @Singleton
    @Provides
    fun providesRepository(): Repository = RepositoryImpl()


    @Provides
    fun providesGson(): Gson = Gson()//можно сконфигурировать свой Gson со своими конфигурациями


    @Provides
    fun providesHttpClient(): OkHttpClient = OkHttpClient()

    @Provides
    fun providesOpenWeatherMapApi(gson: Gson): MyApi {//передаем Gson если нужно свой кастомный
        val retrofit = Retrofit.Builder().baseUrl("https://api.openweathermap.org")
            //если не нужен кастомный джейсон,то прото create()
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
        return retrofit.create(MyApi::class.java)
    }
}