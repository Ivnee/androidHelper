package com.geek.infoandroid.Kotlin.coroutines.teory

import android.accounts.NetworkErrorException
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class FlowCoroutines {
    val scope = CoroutineScope(Job())
    //операторы
    //intermediate ->  onEach, onStart, OnCompletion, onEmpty, map, filter, take, zip, combine, withIndex, scan, debounce, distinctUntilChanged, drop, sample.
    //terminal -> collect, single, reduce, count, first, toList, toSet, fold

    private fun flowCreation() {
        val flow = flow {//стандартный флок
            emit("1")
            emit("2")
        }.flowOn(Dispatchers.IO)//меняет диспатчер у этого флоу(поток теперь IO)влияет на все операторы позади себя
        val flowList = listOf("1", "2", "3").asFlow().cancellable()//аналог того что выше (при вызове cancel() в получателе не сработает без .cancellable())
        val flowListOf = flowOf("1", "2", "3")//еще локаничнее
        val flowFunc = ::getData.asFlow()//получаем с метода инфу и делаем из этого флоу

        val flowMap = flowOf(
            "a",
            "b",
            "c"
        ).map { it.uppercase() }//все элементы с большой буквы,в итоге получаем новый флоу(при этом мап не запустит сам флоу)

        /////////////////////channel flow
        val flowChannel = channelFlow {//отправляем данные флоу через каналы
            launch {
                delay(1000)
                send(1)
            }
            launch {
                delay(1000)
                send(2)
            }
        }
    }

    fun transform() {//создали свой интермедиейт оператор, который дважды отправляет каждое значение(intermediate не запускает код)
        val flowStrings = flowOf("s", "d", "s")
        flowStrings.transform { value ->
            emit(value)
            emit(value)
        }
    }
    fun catchFlow(){
        scope.launch {
            flow<Int>{}//тут флоу что-то эмитит
                .catch { println("catch $it") }//кэтч сработает если в коллекте прийдет ошибка(срабатывает только на операторы перед собой,если после будет map{и в нем ошибка ,то будет крэш} )
                .map { it.toString() }//еслиб была ошибка тут,сработал бы только нижний кэтч
                .catch { emit("exception2 $it")}//можно кэтч вызывать дважды и отправиль нашему флоу данные об ошибке эмитом
                .collect {
                    println("collect $it")//если не прийдет он распечатает данные
                }
        }
    }
    fun retryFlow(){
        scope.launch {
            flow<Int>{}//какой то код с эмитом

                .retry(2) {//повторяет наш флоу  ,если вылетела ошибка , дважды(можно использовать вместе с catch())
                    delay(1000)//подождать секунду , прежде чем перезапустить флоу
                    it !is ArithmeticException//если этто не арифметик эксепшн
                }
                .retryWhen { cause, attempt ->//тоже самое ,но ненадо указывать количество попыток,он дает нам номер попытки и ошибку
                    cause is NetworkErrorException && attempt < 5//если ошибка нетворк и количество попыток меньше 5 ,то ретрай
                }
                .collect {
                    println("collect $it")
                }
        }
    }

    suspend fun getData(): String {
        return "1"
    }

}