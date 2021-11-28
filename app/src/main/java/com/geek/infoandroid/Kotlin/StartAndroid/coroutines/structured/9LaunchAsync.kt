package com.example.testcoroutine

import kotlinx.coroutines.*

class `9LaunchAsync` {
    val scope = CoroutineScope(Job())
    fun run() {
//Launch
        scope.launch {
            val job = launch {
                print("start code")
            }//join запускается только внутри корутины
            val job2 = launch {  }
            job.join()//дождаться завершения корутины,без джоина не будет ждать выполнения корутины
            job2.join()//распаралеливание работ
            print("finish code")
        }
        val job3= scope.launch(start = CoroutineStart.LAZY) {  }//отложенный запуск корутины
        job3.start()//корутина запустится в месте вызова start
//Async
        scope.launch {

            val deffered = scope.async { "async result" }//вместо Job получаем deffered(наследник Job)
            val result = deffered.await()//останавливает парент корутину до окончания работы чаилда и возвращает его результат (await запускается внутри корутины)
        }
        //паралельно
        scope.launch {
            val one = async { "str1" }//выполняется секунду
            val two = async { "str2" }//выполняется 2 секунды
            print("${one.await()} ${two.await()}")//результат будет через 2 секунды
        }
        scope.async (start = CoroutineStart.LAZY){  }//также отложенный вызов(запустится с помощью await())
    }
}