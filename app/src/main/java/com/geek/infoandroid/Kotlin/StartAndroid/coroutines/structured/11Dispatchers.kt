package com.example.testcoroutine

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class `11Dispatchers` {
    fun work(){
        val scope = CoroutineScope(Dispatchers.Default)//создается по дефолту,количество потоков = количеству ядер
        CoroutineScope(Dispatchers.IO)//лимит потоков 64 или количество ядер,если их больше 64(ЗАПРОСЫ В СЕТЬ  И С ДИСКА)
        CoroutineScope(Executors.newSingleThreadExecutor().asCoroutineDispatcher())//дефолтный диспатчер
        CoroutineScope(Dispatchers.Main)//запустит в основном потоке
        CoroutineScope(Dispatchers.Unconfined)//не меняет поток ,после суспенд функции, а продалжаен в том же потоке,где был вызван resume
        repeat(6){//запустит код 6 раз
            scope.launch {  }
        }
        //проверка корутины на активность
        var job:Job? = null
        fun someWork(){
            if(job?.isActive == true) return//защита от повторного нажатия
            job = CoroutineScope(Dispatchers.Main).launch {
                getData()
            }
        }
    }

    //функция с простой корутиной,котора имитирует запрос в сеть и вощвращает данне
    private suspend fun getData(): String =
        suspendCoroutine {
            thread {
                TimeUnit.MILLISECONDS.sleep(3000)
                it.resume("Data")
            }
        }
}