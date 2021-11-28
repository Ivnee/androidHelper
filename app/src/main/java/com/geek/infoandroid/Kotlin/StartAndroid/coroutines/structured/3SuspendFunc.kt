package com.example.testcoroutine

import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class `3SuspendFunc` {
    suspend fun coroutine(): Int {//suspend функция
        return suspendCoroutine { continuation ->//создаем корутину, в которой отправляем данные обратно континуэйшену
            continuation.resume(2 + 2)//отсылает данные и возобновляет работу корутины
            continuation.resumeWithException(Throwable())//отправляем в случае ошибки
        }


    }
}