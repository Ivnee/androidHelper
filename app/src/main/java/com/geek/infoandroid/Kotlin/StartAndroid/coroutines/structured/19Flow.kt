package com.example.testcoroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow


class `19Flow` {//cold источник,для каждого подписавшегося начинает генерить данные заного
    suspend fun work(){
        val flow = flow{//создаем флоу
            for (i in 1..10){
                delay(100)
                emit(i)//отправляем данные
            }
        }
        flow.collect { i-> print(i) }//получаем данные,работает только в суспенд функции
    }
}