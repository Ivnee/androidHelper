package com.example.testcoroutine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class `28Flow` :ViewModel(){
//ShearedFlow - его старт не зависит от подписки(hot) и отсылает все данные всем подписчикам
//обычный Flow -cold ,каждый раз создается новый для каждого подписчика и работаеть только когда на него подпишутся
    private val _flow1 = MutableSharedFlow<String>()
    val flow1 = _flow1.asSharedFlow()//конвертируем в обычный shared flow для коллекта данныых
    fun work() {
        MainScope().launch {
            flow1.collect {}//если данные долго обрабатываются то отправка emit-ом будет задержена,т.к emit =suspend fun(чтоб) для этого надо увеличить buffer с помощью replay
        }
    }

    val flowReplay =
        MutableSharedFlow<Int>(replay = 5)//указываем буфер=5(и кэш=5) для данных,на случай если долго обрабатываются получателеи и приходится ждать

    //буфер - компенсирует работу медленных получателей,если не успели обработать отправлчет в буфер
    //кэш - хранит последние данные,для новый подписчиков вставляет весь кэш
    suspend fun asd() {
        flowReplay.replayCache// посмотреть содержимое кэша
        flowReplay.resetReplayCache()//отчистить кэш
        flowReplay.subscriptionCount.map {  }.onEach {  }//количество подписчиков,можно у них вызывть разные функции преобраования

        val flowBuffer = MutableSharedFlow<Int>(extraBufferCapacity = 5)//задает только размер буфера,если будет еще replay = 5 то размер буфера = 10 а кэша = 5
        val flowBuffered =MutableSharedFlow<Int>(
            onBufferOverflow = BufferOverflow.SUSPEND//метод emit(у отпровителя) приостанавливает корутину, пока не освободится буфер(самым быстрым получателям прийдется ждать)
          //onBufferOverflow = BufferOverflow.DROP_OLDEST удаляет старые элементы,если переполнен буфер и добавляет новые
          //onBufferOverflow = BufferOverflow.DROP_LATEST если буфер уже переполнен (получатели не успели обработать), то емит просто не отправляет данные
        )
//SharedIn- чтоб сделать из обычного флоу SharedFlow
        val flow = flow <String>{emit("123")
        emit("321")}
        val locationSharedFlow = flow.shareIn(//делаем из флоу SharedFlow(чтоб он работал 1 на несколько оподписчиков и не зависил от них)
            scope = viewModelScope,//шереду флоу нужен скоуп для создания(пока жив этот скоуп, работает этот шаред флоу)
            started = SharingStarted.Lazily,//когда начать работу флоу
            //1)Eagerly - стартует сразу при создании(даже если нет подписчиков)
            //2)Lazily - старт при появлении первого подписчика и пока не умрет скоуп
            //3)WhileSubscribed - стаарт при первом подписчике, заканчивает когда последний подписчик отписался
            //3)(есть у него параметры-> stopTimeoutMillis - сколько времени ждать до остановки работы с момента ухода последнего подписчика replayExpirationMillis - как долго живет replay кэш после остановки работы)
            replay = 3)//размер буфера и кэша
        flow.stateIn(viewModelScope, SharingStarted.Lazily,3)//аналог шаред ин то превращает в стейт флоу
//StateFlow - хранит в кеше 1 значение(последнее) которое получает каждый новый подписчик(по сути SharedFlow с DROP_OLDEST)
        val _stateFlow1 = MutableStateFlow(0)//создаем стейт флоу для работы
        val stateFlow1 = _stateFlow1.asStateFlow()//для подписки
        stateFlow1.collect { it }//получаем данные(в скоупе)
        val lastValue = stateFlow1.value//посмотреть последнее значение, которое лежит в стейт флоу
            //пример отображения загрузки...
    }
}