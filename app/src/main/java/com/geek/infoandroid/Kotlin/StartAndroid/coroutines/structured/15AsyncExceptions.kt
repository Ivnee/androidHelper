package com.example.testcoroutine

import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class `15AsyncExceptions` {
    fun work(){
        val scope = CoroutineScope(SupervisorJob())
        scope.launch {

            val defered = async { "some code" }

            try {//оборачиваем await в трай кэч ,если нужно чтоб корутина продолжила выполнять код,после await(но корутина уже будет отменена в случае ошибки)
                defered.await()
            }catch (e :Exception){}
        }
    }

//ошибка в суспенд функции
    suspend fun suspend():String{//отменяемая суспенд функция
        return suspendCancellableCoroutine {

            val result :String?= "getFromCache"//если синхронный(сразу возвращаем разультат)то ошибка будет в месте вызова этой функции
            if (result != null) {
                it.resume(result)
            } else {//Если ошибка произойдет в асинхронной части и не будет там поймана в try-catch, то приложение свалится с крэшем.
                // async code
            }
        }
    }
}