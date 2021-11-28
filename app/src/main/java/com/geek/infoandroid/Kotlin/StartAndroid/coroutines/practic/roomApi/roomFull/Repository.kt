package com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.roomFull

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class Repository(
    private val userDao: UserDao,
   // private val userMapperDbToUi: UserMapperDbToUi,//класс для трансформации из UserEntity в User для UI
    //private val userMapperUiToDb: UserMapperUiToDb//класс трансформирует из UI User в UserEntity
) {
    fun getUsers(): Flow<List<User>> {
       // return userDao.getAll().map { userMapperDbToUi(it)}
        return flow {  }
    }
    suspend fun addUser(user: User){
       // userDao.insert(userMapperUiToDb(user))
    }
    suspend fun deleteAllUsers() {
        userDao.deleteAll()
    }
}