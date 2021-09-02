package com.example.testingdagger2

import android.util.Log
import javax.inject.Inject

class Depend1 @Inject constructor() {//инжект если сюда тоже нужны какие-то зависимости
    fun start(){
        Log.d("123","start 1")
    }
}
