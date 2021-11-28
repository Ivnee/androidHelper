package com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.coroutineWithViews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.*

class FlowListenersWithViewModel : ViewModel() {
    private val _searchQuery = MutableStateFlow("")//флоу для едит
    val searchResultFlow = _searchQuery.asStateFlow()//переделываем в обычный стейтфлоу,у которого
        .debounce(500)//??
        .filter { it.length >3 }//если размер больше 3 символов
        .mapLatest { query ->
            //searchUseCase.execute(query)//в юзкейс кладем нашу строчку и там делаем запрос в сеть или дб по данной строке
        }

    val result = searchResultFlow.asLiveData()//либо отправлять это значение с помощью лайф даты
    fun search(query: String) {
        _searchQuery.value = query
    }
}