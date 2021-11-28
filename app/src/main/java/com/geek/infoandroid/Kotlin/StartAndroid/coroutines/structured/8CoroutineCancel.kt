package com.example.testcoroutine

import kotlinx.coroutines.*

class `8CoroutineCancel` {
    fun work() {
        val scope = CoroutineScope(Job())
        val job = scope.launch {
            delay(1000)//Это суспеенд функция,которая делает паузу
            //в суспенд функциях не нужна проверка на isActive,код билдера прервется в месте вызова суспенда и дальше не пойдет(выкенет эксепшн)
        }
        job.cancel()//делает isActive = false  (код в корутие продолжится если мы не поставим if(isActive))
        scope.cancel()//отменяем скоуп ,который отменяет свой Job , который отменяет все Job внутри себя(у дочерних корутин)
    }
}