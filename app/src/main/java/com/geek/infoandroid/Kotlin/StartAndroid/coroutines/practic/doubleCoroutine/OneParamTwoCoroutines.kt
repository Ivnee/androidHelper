package com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.doubleCoroutine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger

class OneParamTwoCoroutines : ViewModel() {
    //Способ 1
    fun foo() {
        viewModelScope.launch {
            var i =
                AtomicInteger(0)//число которое явзяется атомарным и безопасным для работы с ним из разных потоков

            val job1 = launch(Dispatchers.Default) {
                repeat(100000) {
                    i.incrementAndGet()//увеличить и вернуть результат
                }
            }

            val job2 = launch(Dispatchers.Default) {
                repeat(100000) {
                    i.incrementAndGet()
                }
            }
            job1.join()//ожидаем выполнение корутины
            job2.join()//ожидаем выполнение корутины2
        }
    }
//Способ 2
    var i = 0
    @Synchronized//с помощью синхронайза
    fun increment() {
        i++
    }
//Способ 3 (код корутины остается прежним)
    val mutex = Mutex()
    var j = 0
    suspend fun incrementJ(){
        mutex.withLock {
            i++
        }
    }
//корутина для способа 2 и 3
    fun foo2() {
        viewModelScope.launch {
            var i =
                AtomicInteger(0)//число которое явзяется атомарным и безопасным для работы с ним из разных потоков
            val job1 = launch(Dispatchers.Default) {
                repeat(100000) {
                    increment()
                }
            }
            val job2 = launch(Dispatchers.Default) {
                repeat(100000) {
                    increment()
                }
            }
            job1.join()//ожидаем выполнение корутины
            job2.join()//ожидаем выполнение корутины2
        }
    }
    //способ 4
    private val actorChannel = viewModelScope.actor<Unit> {
        for (e in channel){
            i++
        }
    }
    fun fooActor(){//Результат будет правильный, но время работы - достаточно долгое,не подходят если корутины мало работают и только отправляют данные
        viewModelScope.launch {
            val job1 = launch(Dispatchers.Default) {
                repeat(100000) {
                    actorChannel.send(Unit)
                }
            }
            val job2 = launch(Dispatchers.Default) {
                repeat(100000) {
                    actorChannel.send(Unit)
                }
            }
            job1.join()
            job2.join()
            actorChannel.close()
        }
    }

}