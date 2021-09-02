package com.geek.infoandroid.Kotlin.coroutines.practic.scenaries.cache

import androidx.room.Query
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CacheData {

//кэширование данных
    fun fetchData(id: Int): Flow<Data> = flow {//создаем флоу с какими-то данными
        val cachedData = cache.getData(id)//получаем кэш из дь
        if (cachedData != null) {//если он не пустой
            emit(cachedData)//то отправляем его получателю
        }

        val apiData = apiService.fetchData(id)//скачиваем данные из ретрофита
        cache.putData(id, apiData)//сохраняем их в кэш
        emit(apiData)//отправляем получателю
    }

    //загрузка раз в какое-то время
    fun fetchDataWithPeriod(): Flow<Data> = flow {//возвращаем флоу
        while(true) {//отключится когда отпишутся от флоу
            val data = apiService.fetchData()//загружаем данные с сервера
            emit(data)//отправляем получателю
            delay(10_000)//ждем 10 сек

            /*Когда корутина получателя будет отменена, то цикл прервется.
            Если нужна обработка ошибок, то не забывайте про Flow операторы catch, retry, retryWhen. Либо просто оборачивайте вызов apiService в try-catch.
            Если надо получить Flow, который будет работать на несколько получателей, то используйте shareIn или stateIn.*/
        }
    }

// переключение получения данных из бд 1) получаем любые изменения данных или 2) получаем данные единожд
 //Dao
/* @Query("SELECT * FROM data")
    suspend fun getAll(): List<Data>

    @Query("SELECT * FROM data")
    fun getAllFlow(): Flow<List<Data>>*/

    fun getData(): Flow<Data> = flow {//общий флоу
        if (refreshIsEnabled) {//если чекбокс включен
            emitAll(dataDao.getAllFlow())//то получаем все актуальные данные из дб с помощью флоу
        } else {
            emit(dataDao.getAll())//иначе эмитим единожды
        }
    }
}