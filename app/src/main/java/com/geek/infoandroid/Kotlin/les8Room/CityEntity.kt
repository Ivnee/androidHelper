package com.example.myweather.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityEntity(
    @PrimaryKey
    val id: String,
    val lng: Double,
    val lat: Double,
    val name: String
)
