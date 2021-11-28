package com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.apiRoomFull.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserDb::class], version = 0)
abstract class UserDataBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}