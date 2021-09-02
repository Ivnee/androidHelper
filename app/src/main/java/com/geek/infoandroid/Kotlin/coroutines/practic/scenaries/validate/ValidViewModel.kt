package com.geek.infoandroid.Kotlin.coroutines.practic.scenaries.validate

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.mapLatest

class ValidViewModel:ViewModel() {

    private val _name = MutableStateFlow("")
    private val _age = MutableStateFlow("")


    fun setName(name: String) {
        _name.value = name
    }

    fun setAge(age: String) {
        _age.value = age
    }

    val filteredData: LiveData<String> = combine(_name, _age) { name, age ->
        Filter(name, age)
    }.mapLatest { filter ->//Преобразуем данные наших флоу в данные из fetchData(если фетч дата возвращает Flow<String>,то нужно .flatMapLatest)
        fetchData(filter)
    }.asLiveData()

    private fun fetchData(filter: Filter):String {
        if(filter.name == "" && filter.age == ""){
            return "нет данных"
        }else{ return filter.name + filter.age}
    }
    //Оператор mapLatest будет отменять текущий запрос fetchData, если пришел новый объект Filter (когда поменялся текст в одном из Flow).
    //А asLiveData конвертирует Flow в LiveData, которую можно использовать в биндинге для отображения полученных данных.


    //Лайфдата для валидации,на нее подписываемся в валидэйт фрагменте
    val dataIsValid: LiveData<Boolean> = combine(_name, _age) { name, age ->
        isNameValid(name) && isAgeValid(age)//если имя и возраст введены валидно,то лайфдата отправляет тру и мы активируем кнопку ,иначе нет
    }.asLiveData()//превращаем комбайн 2х корутин в лайф дату


//функции затычки чтоб убрать ошибки
    private fun isAgeValid(age: String): Boolean =true
    private fun isNameValid(name: String): Boolean = true
}
data class Filter(val name: String,val age: String)