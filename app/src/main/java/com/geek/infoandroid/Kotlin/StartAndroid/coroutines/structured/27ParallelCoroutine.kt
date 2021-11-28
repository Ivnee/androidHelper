package com.example.testcoroutine

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import java.lang.Exception

class `27ParallelCoroutine` {
    //задача получить 2 результата работы ,и чтоб они выполнялись паралельно друг другу
    class MainViewModel() : ViewModel() {
        fun work() {
            // передаем хэндлер в ланч ,чтоб приложение не крашнулось(но корутины вссе равно отменится)
            val handler = CoroutineExceptionHandler{context,exception ->
                Log.d("TAG", exception.printStackTrace().toString())
            }
            viewModelScope.launch (handler){
                val result1 = async {"123"}//здесб должны выполняться суспенд функции(чтобы не блокировать поток вью модел скопа(мэин))
                val result2 = async (Dispatchers.IO){"456"}//либо можно явно указать диспатчер,чтоб отработать в другом потоке
                try {//если ошибка ,то она попадет в хэндлер и остановит корутину,трай кеч для того чтоб доделался оставшийся блок кода (hideLoading())
                    print(result1.await() + result2.await())//в этом месте блокируется родительская корутина и ждет результата(работа будет паралельной)
                }catch (e:Exception){}
                //hideLoading
            }
        //задача чтоб не отменялись все корутины при возникновении ошибки(2 способа)
            //способ 1
            viewModelScope.launch {
                val result1 = async {
                    try {
                        //fetch1()просто оборачиваем весь код внутри асинк в трай кэч и избегаем  ошибки в корутине
                    } catch (e: Exception) {
                    }
                }
            }
            //способ 2 если ошибки внутри не избежать
            viewModelScope.launch() {
                val result = try {
                    coroutineScope {//просто создаем корутину скоуп(которая делает новую связть между родителями и дочками) и оборачиваем в трай кеч(корутина скоуп перехватывает исключения и кидает сразу в код)
                        val result1 = async { "123" }//отменятся только все корутины внутри корутина скоупа
                        val result2 = async { "456" }//если надо чтоб внутри coroutineScope не отменялись остальные, используйте SupervisorScope()
                        result1.await() + result2.await()
                    }
                } catch (e: Exception) { }
                //hideLoading()
            }
        }
        //Распаралеливание для suspend функций
        suspend fun fetchUsers() {//если в одной из асинков будет ошибка,корутине скоп выбросит ее в код(в месте вызова этой функции)
            val usersApi = coroutineScope {//поэтому надо будет обернуть это функцию в трай кетч в месте вызова
                val activeUsersApi = async { "123"}//асинки распаралелят запрос и работа займет меньше времени (чем еслиб выполнялось последовательно)
                val inactiveUsersApi = async { "456" }
                activeUsersApi.await() + inactiveUsersApi.await()
            }
        }
    }
}