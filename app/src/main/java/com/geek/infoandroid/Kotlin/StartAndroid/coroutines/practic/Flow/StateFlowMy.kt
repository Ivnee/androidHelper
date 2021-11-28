package com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.Flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

class StateFlowMy:ViewModel() {//если отправитель шлет данные ккоторые равны предыдущим отправленным,то новые просто отбрасываются
    private val _flow = MutableStateFlow("str")//для отправки
    val flow1 = _flow.asStateFlow()//для получателей(у него будет вызываться flow1.collect{})

    fun foo(){
        _flow.value = "data"//отправляем данные(получаем через коллект)
        val lastValue = flow1.value

        flow<String> {  }.stateIn(viewModelScope, SharingStarted.Eagerly,0)//все параметры аналогичны sharedIn ,только эта флоу превращает в стейт флоу
    }
}