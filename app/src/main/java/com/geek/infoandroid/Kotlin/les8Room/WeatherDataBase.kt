package com.example.myweather.storage

import androidx.room.Database
import androidx.room.RoomDatabase
//при изменении таблицы нужно менять версию,экспорт схема
@Database(entities = [WeatherEntity::class,CityEntity::class],version = 1,exportSchema = false)
abstract class WeatherDataBase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}