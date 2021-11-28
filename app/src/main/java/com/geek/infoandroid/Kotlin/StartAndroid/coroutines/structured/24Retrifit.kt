package com.example.testcoroutine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.http.GET

class `24Retrifit` {

    interface CommentApiService {
        @GET("comments")//делаем суспенд чтоб получить выполнение в корутине и не требовалось заморачиваться с потоками
        suspend fun getComments(): Response<List<Comment>>//оборачиваем в response чтоб можно было проверить на isSuccessful()
    }

    class MainViewModel(val service: CommentApiService) : ViewModel() {
        val commentsLiveData = MutableLiveData<List<Comment>>()
        fun fetchData() {
            viewModelScope.launch {
                val response = service.getComments()//получаем данные
                if (response.isSuccessful) {//если нет ошибки сети
                    commentsLiveData.value = response.body()//отправляем их в лайфдату
                }
            }
        }
    }

}

class Comment() {}