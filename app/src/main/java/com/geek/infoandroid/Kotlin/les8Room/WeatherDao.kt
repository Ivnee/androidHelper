package com.example.myweather.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {

    @Query("SELECT * FROM WeatherEntity WHERE cityId = :cityId")
    suspend fun weatherForCity(cityId:String):WeatherEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(entity: WeatherEntity)
}