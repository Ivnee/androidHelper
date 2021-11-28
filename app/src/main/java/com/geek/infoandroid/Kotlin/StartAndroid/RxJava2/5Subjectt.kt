package com.example.testrxjava

import rx.Observable
import rx.Observer
import rx.functions.Action1
import rx.subjects.*
import java.util.concurrent.TimeUnit

class `5Subjectt` {
    //PublishSubject
    private val observer1 = object : Observer<Long> {
        override fun onCompleted() {}
        override fun onError(e: Throwable?) {}
        override fun onNext(t: Long?) {}
    }

    private val observer2 = object : Observer<Long> {
        override fun onCompleted() {}
        override fun onError(e: Throwable?) {}
        override fun onNext(t: Long?) {}
    }
    private val observable = Observable.interval(1,TimeUnit.SECONDS)
        .take(10)

    private val subject = PublishSubject.create<Long>()//создаем сам паблиш сабжект
    fun foo(){//можно сабжект подписать на 2 обсервабла,в таком случае все подписчики сабжекта будут получать данные с 2-х обсерваблов
        observable.subscribe(subject)//старт отправки данныъ (все подписчики сабжекта получат данные от обсервабл)
        subject.subscribe(observer1)//обсервер 1 начал получать данные,с момента подписки
        subject.subscribe(observer2)//обсервер 2 начал получать данные с момента подписки
        subject.onNext(100L)//отправили 100 всем обсерверам
    }
    //ReplaySubject - все данные хранит в буфере
    private val replaySubject = ReplaySubject.create<Long>()//тоже самое ,но данные будут в буфере
    private fun foo2(){
        observable.subscribe(replaySubject)//начало отправки данных
        replaySubject.subscribe(observer1)//после подписки получит вссе данные которые пропустил
        replaySubject.subscribe(observer2)//тоже получит все что пропустил
        replaySubject.onNext(123L)//затем будут получать данные одновременно все обсерверы
        ////
        replaySubject.getValue()// - получить последний элемент
        replaySubject.getValues()// - получить все хранимые данные
        replaySubject.hasAnyValue() //- хранит ли Subject какие-либо данные
        replaySubject.size()// - кол-во хранимых данных
    }
    //BehaviorSubject- имеет размер буфера = 1(выдает последний доступный элемент при подписке )или ,если нет данных, выдает дефолный
    private val behaviorSubject = BehaviorSubject.create<Long>(-1)//-1 это дефолтное значение,которое получит обсервер,если еще нет никаких данных
    private fun foo3(){
        behaviorSubject.subscribe(observer1)//сабжект еще не подписан на обсервабл и данных в нем нет, обсервер 1 получит -1(дефолт)
        observable.subscribe(behaviorSubject)//сабжект подписался на обсервабл и обсерверы сабжекта сразу начнут получать данные
        behaviorSubject.subscribe(observer2)//обсервер 2 получит последнее значение,которое получал сабжект и потом начнет получать в стандартном режиме все данные
    }
    //AsyncSubject - Выдает только последнее значение и только в момент, когда последовательность завершена.
    private val asyncSubject = AsyncSubject.create<Long>()//тоесть завершающее значение приходит ,и только оно поподает в обсерверы

    private fun foo4(){
        observable.subscribe(asyncSubject)//начало отправки данных
        asyncSubject.subscribe(observer1)
        asyncSubject.subscribe(observer2)//получат значение только после метода onComplete
        //
        asyncSubject.hasValue()// - приходил уже результат или еще нет
        asyncSubject.getValue()// - получить результат
    }
    //UnicastSubject - можно подписать лишь одного получателя. даже после того как этот один получатель отписался, никто больше не сможет подписаться.
    private val unicastSubject = UnicastSubject.create<Long>()
    private fun foo5(){
        observable.subscribe(unicastSubject)//начало работы
        val subscription1 = unicastSubject.subscribe(observer1)//обсервер получит  все данные ,которые приходили
        asyncSubject.subscribe(observer2)//обсервер 2 не можежт подписаться ,уже есть обсервер 1
        subscription1.unsubscribe()//обсервер 1 отписался
        asyncSubject.subscribe(observer2)//но обсервер 2 все равно не может подписаться
    }
    //SerializedSubject
    //создаем обычный сабжект
    private val publishSubjecct = PublishSubject.create<Long>()
    //вставляем его в сериалайз сабжект
    private val serializedSubject = SerializedSubject<Long,Long>(publishSubjecct)//делает потокобезопасной работу с данными
    private val action= object: Action1<Long>{
        var count = 0L
        override fun call(value: Long) {
            count += value
        }
        override fun toString(): String {
            return count.toString()
        }
    }
    fun foo6(){
        publishSubjecct.subscribe(action)//подписываем наш получатель
        Thread{
            for(i in 0..100_000){
                serializedSubject.onNext(1L)//100 000 отправляем 1 нашим подписчикам
            }
        }
        Thread{
            for(i in 0..100_000){
                serializedSubject.onNext(1L)//100 000 отправляем 1 нашим подписчикам
            }
        }
        //Результат в сериалайз сабжикте получится 200 000 а без него работа будет не потокобезопасна и получим другое значение
    }
}
/*
Напоследок несколько слов об общих методах для всех Subject
Subject.toSerialized() или FlowableProcessor.toSerialized(). - создает SerializedSubject обертку для вашего Subject.

hasObservers подскажет есть ли у вашего Subject подписчики.

asObservable - вернет Observable обертку для вашего Subject. Это может быть полезным,
когда в вашем классе есть Subject, и вам надо предоставить его для внешних подписчиков. Но если вы вытащите
наружу Subject, то любой сможет вызвать его методы onNext, onError и onCompleted. Скорее всего это нарушит логику
работы вашего класса. Поэтому вы можете вернуть просто Observable, используя метод asObservable, и внешние объекты
смогут только подписываться.*/
