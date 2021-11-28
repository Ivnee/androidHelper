package com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.roomFull

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class],version = 0)
abstract class UserDataBase:RoomDatabase(){
    abstract fun getUserDao(): UserDao
}