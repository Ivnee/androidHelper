package com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.apiRoomFull.retrofit

import retrofit2.http.GET

interface ApiService {
    @GET("Users")
    suspend fun fetchUsers():List<UserApi>
}