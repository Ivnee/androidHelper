package com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.roomFull

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val name:String
)
