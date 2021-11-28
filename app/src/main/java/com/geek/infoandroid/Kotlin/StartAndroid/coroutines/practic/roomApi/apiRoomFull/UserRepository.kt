package com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.apiRoomFull

import com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.apiRoomFull.retrofit.ApiService
import com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.apiRoomFull.retrofit.UserApi
import com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.apiRoomFull.room.UserDao
import com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.apiRoomFull.room.UserDb
import com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.roomFull.User

class UserRepository(
    private val userDao: UserDao,
    private val apiService: ApiService,
    private val userMapperDbToUi: UserMapperDbToUi,//пересоздание данных из дб в ui модель
    private val userMapperApiToDb: UserMapperApiToDb//наоборот
) {
    suspend fun getUsers(): List<User> {
        return userMapperDbToUi.transformList(userDao.getAll())
    }

    suspend fun fetchUsers() {
        val usersApi = apiService.fetchUsers()
        val usersDb = userMapperApiToDb.transformList(usersApi)
        userDao.deleteAll()
        userDao.insertAll(usersDb)
    }
}

//классы затычки
class UserMapperApiToDb {
    fun transformList(aa:List<UserApi>):List<UserDb>{
        return listOf()
    }
}
class UserMapperDbToUi {
    fun transformList(aA:List<UserDb>):List<User>{
        return listOf()
    }
}
