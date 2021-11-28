package com.example.testrxjava

import rx.Observable
import rx.Observer
import rx.Scheduler
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit
//для AndroidSchedulers.mainThread() нужно подключить
//compile 'io.reactivex:rxandroid:1.2.1'
class `6SubscribeOnObserveOn` {
    private val observer= object: Observer<Int>{
        override fun onCompleted() {}
        override fun onError(e: Throwable?) {}
        override fun onNext(t: Int?) {}
    }

    private val onSubscribe =object: Observable.OnSubscribe<Int> {
        override fun call(t: Subscriber<in Int>?) {//метод работы обсервабла ,который вызывается обсерверами
            for (i in 0..3) {
                TimeUnit.MILLISECONDS.sleep(100)
                t?.onNext(i)
            }
            t?.onCompleted()
        }
    }
    private val observable = Observable.create(onSubscribe)//создали кастомный обсервабл
        .subscribeOn(Schedulers.io())//метод Call выполнится в отдельном потоке(неважно в каком месте вызывается,с начале или в конце)
        .observeOn(AndroidSchedulers.mainThread())//методы onNext и onComplete выполнятся в мэйн потоке(методы внутри обсервера)

    //Schedulers.io - для выполнения IO операций (сеть, диск)
    //Schedulers.computation - для выполнения вычислений
    //Schedulers.newThread - шедулер с произвольным новым потоком

    fun work (){
        observable.subscribe(observer)
    }

    ///////1
    private val func1 = object: Func1<Int,Int>{
        override fun call(t: Int): Int {
            return t*10
        }
    }
    private val observable2 = Observable.create(onSubscribe)//создаем кастом обсервабл
        .subscribeOn(Schedulers.io())//создаем наш обсервабл в потоке ио
        .map(func1)//преобразуем все наши данные
        .observeOn(AndroidSchedulers.mainThread())//func1 будет выполнен в io потоке,потому что observeOn стоит после, а методы onNext в мэйн потоке

    ///////2
    private val observable3 = Observable.create(onSubscribe)
        .subscribeOn(Schedulers.io())//оператор create выполнится в io потоке
        .observeOn(Schedulers.computation())//map выполнится в computation
        .map(func1)
        .observeOn(AndroidSchedulers.mainThread())//подписчики выолнятся в main потоке//так как задали этот поток последним
}