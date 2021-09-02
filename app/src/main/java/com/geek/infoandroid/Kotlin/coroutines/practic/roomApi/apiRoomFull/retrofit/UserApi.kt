package com.geek.infoandroid.Kotlin.coroutines.practic.apiRoomFull.retrofit

import com.google.gson.annotations.SerializedName

data class UserApi(
    @SerializedName("id")
    val id:Long,
    @SerializedName("name")
    val name:String
)