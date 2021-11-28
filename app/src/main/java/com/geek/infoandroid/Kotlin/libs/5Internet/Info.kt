package com.geek.infoandroid.Kotlin.libs.`5Internet`

import android.app.Fragment
import android.content.Context
import android.net.ConnectivityManager

class Info : Fragment() {
    fun work() {
//        <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

        val connectivityManager = (Context.CONNECTIVITY_SERVICE) as ConnectivityManager//проверка на подключение к интернету(нужен пермишен в манифесте)
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
        }
    }
}