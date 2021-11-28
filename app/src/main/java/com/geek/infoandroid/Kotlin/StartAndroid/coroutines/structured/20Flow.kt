package com.example.testcoroutine

import kotlinx.coroutines.flow.*
//в этом уроке создаются кастомные операторы
class `20Flow` {
    suspend fun work(){
    //1билдер флоу
        val flow = flow{//обычный флоу билдер
            emit(1)
            emit(2)
        }

        val listFlow = listOf<Int>(1,2,3,4,5).asFlow()//флоу  из листа
        val flowOff = flowOf(1,2,3,4,5,6)//принимает варарг

        flow{emit(emitAll(flow)) }//emitAll сразу все получает


        //Intermadiate операторы - добавляют преобразование данных в флоу , но не запускают его
            // map, filter, take, zip, combine, withIndex, scan, debounce, distinctUntilChanged, drop, sample.
        val flow2 = flowOf(1,2,3,4,5).map { it*10 }//все элементы умножаем на 10,начнет генерить после .collect()

        //Terminal операторы - запускают флоу и работают с резльтатом его работы
            // collect, single, reduce, count, first, toList, toSet, fold.
        val count = flowOf(1,2,3,4,5).count()//сразу запустит и вернет количество элементов,вызывать в suspend func
    }
}