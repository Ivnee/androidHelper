package com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.scenaries.cache

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.actor

//корутина с каналом внутри
class Actor: ViewModel() {

    private val actorChannel = viewModelScope.actor<Unit> {//ставим юнит ,потому что нам не нужно передавать никакиз данных в канал(только событие клика)
        for (click in channel) {
            val data = apiService.getData()//загпузить данные с апм
            //println(data)//что-то с ними сделать
        }
    }

    fun onButtonClick() {//метод который будет установлен на нажатие кнопки
        actorChannel.offer(Unit)//если получатель не готов,оффер выполнится ничего не отправив //передаем юнит(ничего) в канал,чтоб вызвать корутину(она начнет загружать данные)
    }
    fun closeChannel(){//остановиьб работу actor
        actorChannel.close()//Корутина доработает текущую итерацию for до конца и завершится.
        //или отменить скоуп в котором находится наш эктор
    }
}