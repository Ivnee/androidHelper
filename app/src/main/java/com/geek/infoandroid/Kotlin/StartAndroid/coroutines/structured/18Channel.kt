package com.example.testcoroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch

class `18Channel` {
    val scope = CoroutineScope(Job())
    fun work() {//канал потокобезопасен и
        val channel = Channel<Int>()//создаем самый обыный канал
        scope.launch {//каждый метод send ждет метод recive
            launch {
                //1
                channel.send(5) //отправить данные в канал(send - suspend)
                channel.send(6)
                //2
                channel.close()}//закроет канал(дает понять что данные больше не будут отправляться)
            launch {
                //1
                val number = channel.receive()//получить данные(receive - suspend) после close()данные все равно можно получать
                //2
                for(element in channel){val value =element}//будет получать данные пока не будет вызвал close(),после код пойдет дальше
                channel.consumeEach { element -> print(element) }//еще один вариант получения данных обернутый в трай кэч
            }

            val channel2 = Channel<Int>(2)//по умолчанию размер буфера = 1 ,можно указать свой размер бууфера(на случай если данные отправляются быстрее чем получаются)
            val channelConflated = Channel<Int>(Channel.Factory.CONFLATED)//не ждет receiver а шлет данные сразу,если не успели забрать,то перезаписывает(размер буфера = 1)
            val channelUnlimited = Channel<Int> (Channel.Factory.UNLIMITED)//размер буфера ограничен только памятью
            val channelBuffer = Channel<Int>(Channel.Factory.BUFFERED)//размер буфера 64
            channel.trySend(5)//если получателя нет,то данные просто не уйдут
            channel.close()//закрывает канал,но данные все равно можно получить
            channel.cancel()//закрывает канал и еще отчищает весь кэш
            channel.isClosedForReceive//канал закрыт для получения?
            channel.isClosedForSend//канал закрыт для отправки ?

            val channelProduce =produce<Int>{//сразу создаем канал ,который запустит свою корутину(launch не нужен)
                send(1)//этот канал закрывается автоматически ,после своего выполнения
                send(2) }
        }
        //broadcast Channel - отсылает данные всем получателям
        //в обычном канале все данныые будут распределяться ровномерно между получателями
        val channelBroadcast = BroadcastChannel<Int>(1)//канал для отправки данным всем
        val channelReceiveBroadcast = channelBroadcast.openSubscription()//канал для получения данных из бродкаста ченлл
        scope.launch {
            launch {  channelBroadcast.send(5) }//отправляем данные
            launch { channelReceiveBroadcast.receive() }//получаем данные
        }
    }
}