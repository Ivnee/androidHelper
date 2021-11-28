package com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.Flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

//не кидает данные при каждом вызове коллект у получателя,а отправляет всем, когда дергается сам emit
class SharedFlowMy : ViewModel() {
    //shared flow работает в одном экземпляре ,не привязан к получателям(collect)может сразу нескольким отдавать данные
//State flow создает на каждый коллект свой флоу (если 2 получателя 2 флоу)а shared flow 1 на всех
    private val _myFlow = MutableSharedFlow<String>()//для отправителей
    val myFlow =
        _myFlow.asSharedFlow()//для получаетелй конвертируем в SharedFlow(аналог liveData mutableLiveData)

    fun foo() {
        viewModelScope.launch {
            _myFlow.emit("dannie")//отправляем какие-то данные получателю
        }

    }


    private val _myFlow2 =
        MutableSharedFlow<String>(replay = 3)//(увеличивает буфер и кэш)пока получатель не загрузит данные , он не сможет выполнить следующее

    fun foo2() {

        _myFlow2.replayCache//чтобы посмотреть содержимое кэша
        _myFlow2.resetReplayCache()//отчистить кэш,доступно только отправителю(mutableSharedFlow)
    }

    //параметры MutableSharedFlow
    val flowBuffer = MutableSharedFlow<String>(
        replay = 3,
        extraBufferCapacity = 4
    )//размер буфера будет равен сумме значений этих параметров: 3+4=7. Но размер кэша будет равен 3.
    val flowBuffer2 = MutableSharedFlow<String>(extraBufferCapacity = 4)//размер кэша будет 0
    val flowBufferOverflow =
        MutableSharedFlow<String>(onBufferOverflow = BufferOverflow.SUSPEND)//задает поведение ,когда отправитель шлет данные,но буфер переполнен
    //SUSPEND-метод emit на стороне отправителя будет приостанавливать корутину, пока не появятся свободные слоты в буфере.
    //DROP_OLDEST -метод emit будет удалять из заполненного буфера наиболее старые элементы и добавлять новые.
    //DROP_LATEST - метод emit не будет отправлять новые значения, если буфер заполнен

    val stateFlow1 = MutableStateFlow<String>("str")//обычный стейт флоу
    val locationShFlow = stateFlow1.shareIn(//трансформируем обычный флоу в SharedFlow(коллект вызываем у locationShFlow и он будет подключен к сервису скоторого получает данные 1 раз(как любой шаред флоу))
        scope = viewModelScope,// в каком скоупе будет выполняться этот флоу
        started = SharingStarted.Lazily,//Eagerly - работа в Flow стартует сразу при создании SharedFlow, даже если еще нет подписчиков.
        //Lazily - стартует при появлении первого подписчика. Flow будет работать, пока не отменим scope.
        //WhileSubscribed - стартует при появлении первого подписчика. При уходе последнего подписчика - останавливается
        replay = 3)//буфер и кэш

}