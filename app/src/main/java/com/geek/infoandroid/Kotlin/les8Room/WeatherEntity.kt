package com.example.myweather.storage

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myweather.domain.City

@Entity(tableName = "WeatherEntity")
data class WeatherEntity(
    @PrimaryKey
    val cityId:String,
    @Embedded
    val city: CityEntity,
    val temperature: Double)