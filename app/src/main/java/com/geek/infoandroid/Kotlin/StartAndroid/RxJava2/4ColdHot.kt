package com.example.testrxjava

import rx.Observable
import rx.Observer
import java.util.concurrent.TimeUnit


class `4ColdHot` {
    //COLD
    //observers
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

    //observable
    private val observable = Observable.interval(1, TimeUnit.SECONDS)
        .take(5)//достанет только первые 5 элементов данных

    //subscribe
    fun foo() {
        // Observable для каждого нового подписчика начинает генерировать данные с начала.
        // Observable начинает свою работу в момент подписки.
        observable.subscribe(observer1)//получит 0 1 2 3 4
        observable.subscribe(observer2)//и обсервер 2 тоже 0 1 2 3 4
    }

    //HOT
    //Observers
    private val observer11 = object : Observer<Long> {
        override fun onCompleted() {}
        override fun onError(e: Throwable?) {}
        override fun onNext(t: Long?) {}
    }

    private val observer22 = object : Observer<Long> {
        override fun onCompleted() {}
        override fun onError(e: Throwable?) {}
        override fun onNext(t: Long?) {}
    }

    //Observable
    private val connectableObservable = Observable.interval(1, TimeUnit.SECONDS)
        .take(6)
        .publish()//публишь делает из него хот обсервабл и создает объект ConnactableObsevable

    fun foo2() {
        connectableObservable.connect()//начнет оправлять данные независимо  от того есть слушатели или нет
        connectableObservable.subscribe(observer11)//начнут получать данные только после подписки, приходить будут данные одинаковые и одновременно
        connectableObservable.subscribe(observer22)
        //как остановить хот ообсервер
        val subscriprion = connectableObservable.connect()
        subscriprion.unsubscribe()//остановит работу обсервабла
    }

    //REPLY аналог Hot(publish),Но вновь прибывшие подписчики будут получать элементы, которые они пропустили.
    private val connectableObservable2 = Observable.interval(1, TimeUnit.SECONDS)
        .take(6)
        .replay()//описание где сабскрайбы(ниже)

    fun foo3() {
        connectableObservable2.connect()//так же начинаем работу
        connectableObservable2.subscribe(observer11)//сразу же после подписки обсерверы получают все ээлементы,которые пропустили
        connectableObservable2.subscribe(observer22)//остальные получает как обычно(в данном случае раз в секунду)
    }

//RefCount  начинает работать при первом появившемся подписчике, и заканчивал после того, как отпишется последний.
//будет раздавать одни и те же данные всем подписчикам
    private val observable3 = Observable.interval(1, TimeUnit.SECONDS)
        .take(6)
        .publish()//публишь делает из него хот обсервабл и создает объект ConnactableObsevable
        .refCount()//так же является хотом,отправляем данные всем(оборачивает обять в обычный обсервабл)
    fun foo5 (){
        val subscription1 = observable3.subscribe(observer11)//начало работы обсервабла
        val subscription2 =observable3.subscribe(observer22)//получают одинаковые значения(хот)
        subscription1.unsubscribe()
        subscription2.unsubscribe()//конец работы обсервабла
        observable.subscribe(observer11)//по новой начало работы обсервабла

    }
//CACHE  Он начинает работу при первом подписчике, хранит все элементы и выдает их каждому новому подписчику (даже если он пропустил).

    private val observable4 = Observable.interval(1, TimeUnit.SECONDS)
        .take(10)
        .cache()//как replay + refCount
    fun foo6(){
        val subscription1= observable4.subscribe(observer11)//начало работы обсервабл
        val subscription2=observable4.subscribe(observer22)//докинет данные которые пропустил этот обсервер
        subscription1.unsubscribe()
        subscription2.unsubscribe()//конец работы обсервабл
        observable.subscribe(observer11)//начала работы обсервабл по новой
    }
}
