package com.example.testcoroutine

import kotlinx.coroutines.*

class `17Scopes` {

    fun work (){
//1coroutineScope - принимает ошибки от детей и не шлет их дальше,а выкидвает в код
        // работает в том же потоке,в котором был вызван(продолжить вызвавшую его корутину может в другом )
        val scope = MainScope()
        scope.launch(CoroutineName("1")) {

            try {//оборачиваем coroutineScope в трай кеч,если не хотим чтоб ошибка убила приложение
                //возвращает результат работы и является суспенд функцией(приостанавливает код)если нам это не надо ,засовываем его в launch или async
                val result = coroutineScope {//передает ошибку сразу нам в код а не наверх ,своим родителям(в него нельзя вставить CoroutineExceptionHandler)
                    launch(CoroutineName("1_1")) {
                        // ...
                    }

                    launch(CoroutineName("1_2")) {
                        // exception
                    }
                }
            } catch (e: Exception) {
                //отловит ошибку,которую выбрасит coroutineScope в наш код
            }
            launch(CoroutineName("1_3")) {}
            launch(CoroutineName("1_4")) {}
        }
//2SupervisorScope- чтобы обработать ошибку в супервизор скоп , нужно просто использовать CoroutineExceptionHandler
    // и ошибка не пойдет дальше и не отменит остальные корутины

//3WithContext - используется для заменты элементов контекста корутины(чаще всего Dispatchers)
        scope.launch {
            //основной поток
            withContext(Dispatchers.Main) {//переводит работу в поток мэйн
            //поток мэйн в котором поработали с вью
            }
            //основной поток
        }
//4RunBlocking - используется для Unit тестов в основном
    //запускает корутину, которая блокирует текущий поток, пока не завершит свою работу
    // (и пока не дождется завершения дочерних корутин).


    }
}