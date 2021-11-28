package com.example.testcoroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

//ВСЕ ЭТИ ОПЕРАТОРЫ ПОД КАПОТОМ ИСПОЛЬЗУЮТ CAHNNEL
class `21FlowOperators` {
    //channelFlow
    val flow = channelFlow {//флоу который может запускать внутри себяя ланчи (для того чтоб была паралельная работа)
        launch {
            delay(1000)
            send(1)//отправляет данные блоку collect
        }
        launch {
            delay(1000)
            send(2)
        }
    }
    //callbackFlow(пример в 29 уроке)
    val callbackFlow = callbackFlow<Int> {//так же как ченл флоу,но отличается тем,что проверяет забыли вызвать awaitClose() или нет
        send(1)//если забыли то получим ошибку
        send(2)
        awaitClose()//отписывается от канала,внутреннего
    }
    //flowOn
    val flowOnn = flow{emit(1)}//IO оток
        .map { it * 10 }//IO поток
        .flowOn(Dispatchers.IO)//IO поток(FlowOn переключает поток в котором мы выполняем наш код)
        .onEach {  }//Main поток
        .flowOn(Dispatchers.Main)//оператор флоу влияет на все предыдущие операторы
    //Buffer
    val bufferFlow = flow{emit(1)}
        .buffer(5)//будет копить значение в буфере ,если получатель не будет успеевать их принимать
        .flowOn(Dispatchers.IO)//можно использовать с другими операторами
    //ProduceIn
    val scope = CoroutineScope(Job())//для продьюс ин нужен скоуп
    val channel = flow{
        emit(123)
    }.produceIn(scope)//создает из flow channel и после создания сразу начинает свою работу(получаем через receive())
}
