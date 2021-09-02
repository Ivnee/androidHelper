package com.geek.infoandroid.Kotlin.les6ServiceAndBroadcast

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
//BIND SERVICE
//СЕРВИСЫ НУЖНО ОБЪЯВЛЯТЬ В МАНИФЕСТЕ
class FibonacciService: Service() {

    private val binder = ZelBinder()//создали экземпляр класса байндер

    private var numberOne = 0
    private var numberTwo = 1
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
    inner class ZelBinder: Binder(){//должен быть иннер
        val service:FibonacciService get() = this@FibonacciService
        fun nextNumber() :Int = service.nextNumber()
    }
    fun nextNumber ():Int {
        val res = numberOne + numberTwo
        numberOne = numberTwo
        numberTwo = res
        return res
    }
}