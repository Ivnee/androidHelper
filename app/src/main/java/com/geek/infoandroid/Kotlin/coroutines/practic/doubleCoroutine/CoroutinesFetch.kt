package com.geek.infoandroid.Kotlin.coroutines.practic.doubleCoroutine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import java.lang.Exception

class CoroutinesFetch : ViewModel() {

    fun foo() {

        //чтобы не случился в такой ситуации крэш,нужно добавить хэндлер
        val handler =
            CoroutineExceptionHandler { context, exception ->
                println("handled $exception")
            }
        //////////
        viewModelScope.launch(handler) {//хендлер ловит ошибки,если не будет его тут ,будет крэш
            val result1 =
                async { fetch1() }//асинк сохраняет корутину,для синхронного вызова требуется на каждую суспенд фуфнкцию своя корутина
            val result2 =
                async(Dispatchers.IO) { fetch2() }//выполняютс они в мэйн потоке(если явно не указать деспачер)
            try {//оборачиваем чтоб если будет ошибка в одном из async ,код который идет после эвейтов, завершился
                showResult(result1.await() + result2.await())//метод эвейт получает результат работы
            } catch (exc: Exception) {
            }


        }
    }

    //как избежать отмену всех корутин , в случае если дочерний выдает эксепшн
    //поимка эксепшенов 1
    fun foo2() {
        viewModelScope.launch() {
            val result = try {
                coroutineScope {//промежуточная корутина,которая принимает от дочерней(асинк) и не передает ее ошибку выше,что позволяет продолжить
                    //работу ланча,но внутри самого корутин скопаа все корутины будут отменены
                    val result1 = async { fetch1() }
                    val result2 = async { fetch2() }
                    result1.await() + result2.await()
                }
            } catch (e: Exception) {
            }
            showResult(result)
        }
    }

    //поимка эксепшенов 2
    fun foo3() {
        viewModelScope.launch {
            val result1 = async {
                try {
                    fetch1()//в этом случае он не завершится с ошибкой и не отменит ланч
                } catch (e: Exception) { }
            }
        }
    }

    //как можно вызвать суспенд функцию в юзкейсе с помощью корутина скоупа
    suspend fun usecaseFoo() {//паралелим запросы к серверу с помощью 2-х функций асинк
        val usersApi = coroutineScope {//если в одной из них прилетит ошибка,корутина скоп ее примет и выбросит в месте вызова
            //ПОЭТОМУ В МЕСТЕ ВЫЗОВА НУЖНО ОБЕРНУТЬ ЕЕ В ТРАЙКЭЧ
            val activeUsersApi = async { userApiService.fetchActiveUsers() }
            val inactiveUsersApi = async { userApiService.fetchInactiveUsers() }
            activeUsersApi.await() + inactiveUsersApi.await()
        }
    }
}