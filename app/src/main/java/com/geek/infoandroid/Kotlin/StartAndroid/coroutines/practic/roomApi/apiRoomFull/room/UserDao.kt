package com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.apiRoomFull.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<UserDb>

    @Insert
    suspend fun insertAll(users: List<UserDb>)

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}
