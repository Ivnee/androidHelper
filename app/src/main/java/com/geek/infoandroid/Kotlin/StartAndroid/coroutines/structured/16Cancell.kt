package com.example.testcoroutine

import kotlinx.coroutines.*
import kotlin.coroutines.suspendCoroutine

class `16Cancell` {
    fun work(){
        val job = CoroutineScope(Job()).launch {  }
        job.cancel()//корутина перейдет в состояние isActive= false и отменятся все его дочерние корутны
    }
    suspend fun simpleSuspendFun(){//не обращают внимания на то, что корутина отменилась.
        return suspendCoroutine {  }//Они работают в обычном режиме и возвращают результат.
    }
    suspend fun cancallableSuspendFun(){//при cancel выбрасывает  CancellationException и корутина прекращает свою работу
        return suspendCancellableCoroutine {//сама функция при этом доходит до конца кода
         it.invokeOnCancellation { print("close func") }//этот код вызовется ,когда корутина будет отменена job.cancel
        }
    }
}