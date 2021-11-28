package com.example.testcoroutine

import android.accounts.NetworkErrorException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.*

class `22FlowExceptions` {
    //try Catch
    val flow = flow<Int> { emit(1 / 0) }//
    suspend fun work() {
        try {
            flow.collect { it + 1 }
        } catch (e: Exception) {//все ошибки флоу отлавливаются в блоке коллект ,потому как оттуда начинается работа флоу
        }
        //Catch
        flow.catch { emit(it.stackTrace.size) }//можно использовать 2 кэтча и доступен метод эмит
            .map { it + 1 }//если в мап будет ошибка,то кэтч ее отловит,потому что кэтч стоит после этого оператора
            .catch { print(it.stackTrace) }//отловим ошибку в блоке catch , если ошибка возникла в блоке после оператора кетч,она не будет поймана
            .collect { }//метод коллект можно ставить видимо только после,но ошибка отловится ...хз

        //retry
        val flow2 = flow<Int> { }
            .retry(4) {//повторяет попытку 4 раза,если приходит ошибка (действует так же только на предыдущих операторов)
                if (it is NetworkErrorException) true else false//если это ошибка сети ,то повторяем попытку ,иначе нет
            }
            .retry(1)//можно вызывать без блока кода
            .collect { it }
        //retryWhen
        val flow3 = flow<Int>{}.retryWhen{exception, num->//как ретрай,но вместо количеста попытак дает ошибку и число попыток
            exception is NetworkErrorException && num < 5//если это ошибка сети и номер попытки меньше 5, то повторяем(можно еще отправить данные получателю emit())
        }.collect { }
        //отмена флоу
        flow<Int>{}.collect { currentCoroutineContext().cancel()//отменит флоу(если нужно чтоб работа доделалась,оборачиваем в трай кэч)
         }
        val flow5 = listOf<Int>(1,2,3,4,5).asFlow().cancellable()//научили этот флоу отменяться
    }
}