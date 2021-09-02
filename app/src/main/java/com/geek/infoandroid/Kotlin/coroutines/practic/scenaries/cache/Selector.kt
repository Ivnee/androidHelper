package com.geek.infoandroid.Kotlin.coroutines.practic.scenaries.cache

import kotlinx.coroutines.*
import kotlinx.coroutines.selects.select

class Selector {
    fun funfunfun() {

        CoroutineScope(Job()).launch {
            //2 асинка выполняют запросы с 2-х разных апи
            val async1 = async { ... }
            val async2 = async { ... }

            val result =
                select<String> {//селект сохранит тот результат,который будет первым выполнен,а второй эвейт отменится
                    async1.onAwait {
                        it
                    }
                    async2.onAwait {
                        it
                    }
                }

        }
    }

}