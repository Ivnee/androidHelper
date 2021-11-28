package com.example.testroom.entities

import androidx.room.Entity

@Entity
data class Adress(
    val city:String,
    val street:String,
    val number:Int
)