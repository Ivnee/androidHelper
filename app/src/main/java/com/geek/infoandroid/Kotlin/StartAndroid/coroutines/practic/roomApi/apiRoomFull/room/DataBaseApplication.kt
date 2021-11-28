package com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.apiRoomFull.room

import android.app.Application
import androidx.room.Room

class DataBaseApplication : Application() {
    fun getDb(): UserDao {
        val room =  Room.databaseBuilder(this, UserDataBase::class.java,"userDb").build()
        return room.getUserDao()
    }
}