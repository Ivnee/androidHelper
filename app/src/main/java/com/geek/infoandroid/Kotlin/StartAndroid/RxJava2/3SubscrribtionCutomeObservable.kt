package com.example.testrxjava

import io.reactivex.disposables.CompositeDisposable
import rx.Observable
import rx.Observer
import rx.Subscriber
import rx.functions.Action1
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import java.util.concurrent.TimeUnit

class SubscrribtionCutomeObservable {
    private val observable = Observable.interval(1, TimeUnit.SECONDS)
    private val action = object : Action1<Long> {
        override fun call(t: Long?) {
            print("number -> ${t}")
        }
    }
    //получаем сабскрибшн и подписываем наш экшн
    val subscription = observable.subscribe(action)
    fun foo(){
        subscription.unsubscribe()//отписываем наш экшн от получения событий
    }


    //CompositeSubscribtion (отменяет обсерверы сразу пачкой )
    private val compositeSubscription = CompositeDisposable()
    fun foo2(){
        compositeSubscription.add(subscription)//добавляем n-ное количество сабскрипшинов() объект ,который возвращается при подписке обсервера на обсервабл
        compositeSubscription.add(subscription)
        compositeSubscription.unsubscribe()//отписать всех наблюдателей ,которые были добавлены
        subscription.isUnsubscribed//вернет тру ,если подписка отменена
    }
}
//Создаем свой Observable
private val onSubscribe = object :Observable.OnSubscribe<Int> {//что быдет делать кастомнй осервабл
    override fun call(subscriber: Subscriber<in Int>?) {
        for(i in 0..10){
            TimeUnit.SECONDS.sleep(1)//блокирующий метод
            if(subscriber?.isUnsubscribed == true){
                return
            }
            subscriber?.onNext(i)
        }
        subscriber?.onCompleted()
    }
}
private val observable = Observable.create(onSubscribe)//создаем свой обсервабл
    .subscribeOn(Schedulers.io())//чтобы блокирующие методы не блокировали основной поток
private val observer = object: Observer<Int>{
    override fun onCompleted() {}
    override fun onError(e: Throwable?) {}
    override fun onNext(t: Int?) {}
}
fun foo3(){
    val subscription = observable.subscribe(observer)//подписываемся к обсерваблу
    subscription.unsubscribe()//обязателньо не забываем отписаться ,чтоб небыло утечек памяти
}