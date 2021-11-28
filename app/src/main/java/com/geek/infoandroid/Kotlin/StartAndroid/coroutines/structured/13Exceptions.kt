package com.example.testcoroutine

import kotlinx.coroutines.*
import java.lang.Exception

class `13Exceptions` {
    fun work(){
        val scope = CoroutineScope(Job())
    //1 Способ
        scope.launch {
            try {//просто оборачиваем код внутри корутины в трай кэч
                Integer.parseInt("a")
            }catch (e :Exception){
            }
        }
    //2 способ
  //JOB //ошибка прокидывается на самый верх до скоупа и отменяет каскадно все корутины
        val handler = CoroutineExceptionHandler { coroutineContext, throwable ->//создали обработчик ошибок(1)котекст корутины которая передала ошибку в обработчик,2) ошибка)
            print(throwable.stackTrace)
        }
        scope.launch (handler){  }//при врзникновении ошибки она распечатается и рне будет креша(можно в дочернюю launch(handler))
//SupervisorJob //есби в скопе супервизор ,то ошибка в корутине 1.1 отменит 1 и 1.2 . 2.1 и 2.2 продолжат работу
        val scopeSupervisor = CoroutineScope(
            SupervisorJob()//не отменяет все корутинны ,которые находятся в этом scope,отменяет только те ,кто ниже по уровню
                +Dispatchers.Default
                +handler)


        scope.launch {
            launch(SupervisorJob(coroutineContext[Job])) {  }//костыль для установки супервизор джоба в корутину,так не устанавится ,он только для скоупа
        }
    }
}