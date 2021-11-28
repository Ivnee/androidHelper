package com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.apiRoomFull

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.apiRoomFull.usecase.FetchOrGetUsersUseCase
import com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.roomFull.User
import kotlinx.coroutines.Job

class MainViewModel(private val useCase: FetchOrGetUsersUseCase):ViewModel() {
    val users = MutableLiveData<List<User>>()
    var dataJob: Job? = null

    fun getData(){
        if(dataJob?.isActive == true) return//если джоб еще не выполнился ,мы выходим из функции.Защита от множественного нажатия на кнопку вызова этого метода
        dataJob = viewModelScope.launch {
            //show load
            val result = useCase.execute()
            //hide load
            users.value = result
        }
    }
}