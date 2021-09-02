package com.geek.infoandroid.Kotlin.coroutines.practic.roomFull

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.geek.infoandroid.Kotlin.coroutines.practic.roomFull.usecases.AddUserUseCase
import com.geek.infoandroid.Kotlin.coroutines.practic.roomFull.usecases.DeleteAllUsersUseCase
import com.geek.infoandroid.Kotlin.coroutines.practic.roomFull.usecases.GetUserUseCase
import kotlinx.coroutines.launch
import java.lang.Error

class UserViewModel(
    private val addUserUseCase: AddUserUseCase,
    private val deleteAllUsersUseCase: DeleteAllUsersUseCase,
    private val getUserUseCase: GetUserUseCase
):ViewModel() {
    val users = getUserUseCase.execute().asLiveData()//сразу получаем флоу и конвертируем в лайф дату
    //во вью мы скорее всего получаем так  viewModel.users.observe(viewLifecycleOwner) {adapter.setData(it)}


    fun onAddClick(){//add и clear автоматически тригерят флоу рума, и он сам будет обнавлять нашу лайфдату
        viewModelScope.launch {//запускаем корутину
            val result = addUserUseCase.execute(User(0,"123"))//вызываем суспенд метод для получения результата(еррор или сакцесс пустой)
            if(result is Error){
                print(result.stackTrace)//если ошибка то распечаатаем ее
            }
        }
    }
    fun onClearedClick(){
        viewModelScope.launch {
            deleteAllUsersUseCase.execute()
        }
    }
}