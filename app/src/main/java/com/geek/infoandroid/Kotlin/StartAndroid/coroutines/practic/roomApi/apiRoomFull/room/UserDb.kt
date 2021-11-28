package com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.apiRoomFull.room

import androidx.room.Entity

@Entity(tableName = "user")
data class UserDb(
    val id:Long,
    val name:String
)