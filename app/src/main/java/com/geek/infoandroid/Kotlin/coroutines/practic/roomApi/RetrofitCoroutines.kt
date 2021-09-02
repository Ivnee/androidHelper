package com.geek.infoandroid.Kotlin.coroutines.practic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CommentApiService {

    @GET("comments")
    suspend fun getComments(): Response<List<String>>//респонс для проверки на isSuccessful() ,можно без него

}
class RetrofitCoroutines: ViewModel() {

    val retrofit = Retrofit.Builder().baseUrl("123123").addConverterFactory(GsonConverterFactory.create()).build()
    val service = retrofit.create(CommentApiService::class.java)


    suspend fun retrofitExceptions(){
        viewModelScope.launch {//запускаемся во вью модели
            try {
                val commentsData = service.getComments()//получаем данные из ретрофита
               // liveData.value = commentsData//и лайвдата отобразит наши данные на какой-либо вью
            } catch (e: Exception) {//если ловим ошибку .то обрабатываем ее
                // ...
            }
        }


        //или обработать ошибки можно с помощью самого ретрофита
        //    @GET("comments")
        //    suspend fun getComments(): Response<List<Comment>>//Просто указываем в типа возвращаемого значение Response
        val response = service.getComments()//получаем его из ретрофит апи
        if (response.isSuccessful) {//и проверяем
            val commentsData = response.body()
        }
    }


    //если ретрофит не суспенд и возвращает Call(впринципе это не особо нужно,ретрофит умеет напрямую работать с корутмнами)
    fun withContext(){
        fun fetchData() {
            viewModelScope.launch {//скоуп выполняется в мейн потоке
                val response = withContext(Dispatchers.IO) {//переводим получение данных в IO поток
                    service.getComments()//.execute()//вызываем метод ретрофита для получения данных
                }
                // и здесь делаем какие-то операции с методом респонс
            }
        }
    }

}