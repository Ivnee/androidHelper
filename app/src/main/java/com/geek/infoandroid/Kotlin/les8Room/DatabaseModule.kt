package com.geek.infoandroid.Kotlin.les8Room

import android.app.Application
import androidx.room.Room
import com.example.myweather.storage.WeatherDao
import com.example.myweather.storage.WeatherDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providesWeatherDataBase(application:Application):WeatherDataBase{//необходимо передавать именно аппликейшн контекст,не активишный
        return Room.databaseBuilder(application, WeatherDataBase::class.java,"WeatherDataBase").build()
    }
    @Provides
    fun providesWeatherDao(dataBase: WeatherDataBase): WeatherDao {
        return dataBase.weatherDao()
    }
}