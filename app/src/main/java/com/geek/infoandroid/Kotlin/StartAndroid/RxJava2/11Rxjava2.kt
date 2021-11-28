package com.example.testrxjava

import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableMaybeObserver
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.subscribers.DisposableSubscriber
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.concurrent.Callable
import java.util.function.BiConsumer

class `11Rxjava2` {
    //Flowable поддерживает backpressure так же,как обсервабл в прошлом уроке,а обсервабл вообще не поддерживает его больше
    private val observable = Observable.range(
        1,
        10
    )//теперь не поддерживает onBackpressure(под капотом просто используется onBackpressureBuffer, может вызвать outOfMemory,использовать ,если немного данных или обработка нажатий)
    private val flowable = Flowable.range(
        1,
        10
    )//Flowable поддерживает onBackpressure ,использовать в чтении с диска или интернета

    //Subscribe
    fun work() {
        //OBSERVABLE
        observable.subscribe(object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {//Dispose = Subscribtion,который возвращал метод subscribe(Observer),теперь о ничего не вернет,просто есть новый метод
                d.dispose()//отписаться
                val status = d.isDisposed//вернет тру,если отписан
            }

            override fun onNext(t: Int?) {}
            override fun onError(e: Throwable?) {}
            override fun onComplete() {}
        })
//получаем disposable вне метода onSubscribe
        val disposableObserver = object : DisposableObserver<Int>() {
            //как старый обсервер(представляет из себя одновременно observer и despose)
            override fun onNext(t: Int?) {}
            override fun onError(e: Throwable?) {}
            override fun onComplete() {}
        }
        observable.subscribe(disposableObserver)
        disposableObserver.dispose()//отписаться
        disposableObserver.isDisposed//отписан или нет?
//или получаем диспоус сразу
        val disp = observable.subscribeWith(object : DisposableObserver<Int>() {
            //subscribeWith возвращает подписчика,
            override fun onNext(t: Int?) {}//в данном случае он еще и dispose
            override fun onError(e: Throwable?) {}
            override fun onComplete() {}
        })
        disp.dispose()//отписаться
//FLOWABLE = Основная раздница что вместо observer теперь subscriber
        flowable.subscribe(object : Subscriber<Int> {
            override fun onSubscribe(s: Subscription) {//сабскрибтикн так же как в первой версии(управляет подписой)
                s.request(1)//запрашивает данные,1 единицуу данных
                s.request(Long.MAX_VALUE)//отправить все данные что есть
                s.cancel()//отписаться от получения данных
            }

            override fun onNext(t: Int?) {//здесь ничего не прийдет,если не запрашивать данные в onSubscribe с помощью request(1)
            }

            override fun onError(t: Throwable?) {}
            override fun onComplete() {}
        })
        //DisposableSubscriber- если нужно управлять подпиской самостоятельно
        val disposablee = flowable.subscribeWith(object : DisposableSubscriber<Int>() {
            override fun onNext(t: Int?) {}
            override fun onError(t: Throwable?) {}
            override fun onComplete() {}
        })
        disposablee.dispose()//отписали сабскрайбер
//Single
        val single = Observable.just("1", "2", "3").count()//вернет число элементов
        single.subscribe(object : SingleObserver<Long> {
            //аналог обычного обсервера.но без onComplete
            override fun onSubscribe(d: Disposable?) {}
            override fun onSuccess(t: Long?) {}
            override fun onError(e: Throwable?) {}
        })
        //также можно создать DisposableSingleObserver(как выше у других обсерверов),который вернет disposable.dispose()

//Completable- предоставляет либо onError либо onComplete
        //CompletableObserver - аналог обычного Observer но без onNext
        //DisposableCompletableObserver - аналог DisposableObserver, но без onNext

//Maybe - прийдет либо одно значение в onNext либо onComplete  ,что-то одно
        val maybe = Observable.just("1", "2", "3")
            .elementAt(8)//elementAt вернет элемент с указанным индексом(элемента по этоум индексу нет,поэтому вернет onComplete)
        maybe.subscribe(object : DisposableMaybeObserver<String>() {
            override fun onSuccess(t: String?) {}
            override fun onError(e: Throwable?) {}
            override fun onComplete() {}
        })
        //MaybeObserver - аналог обычного Observer, но с методом onSuccess вместо onNext
        //DisposableMaybeObserver - аналог DisposableObserver, но с методом onSuccess вместо onNext

//Создаем свой Flowable   .create()
        val flowableS =Flowable.create(object :FlowableOnSubscribe<Int>{
            override fun subscribe(emitter: FlowableEmitter<Int>?) {
                for(i in 0..10){
                    emitter?.onNext(i)//передаем данные
                }//теперь ненужно проверять isUnsubscribed(),если подписчик отпишется,эмитер просто будет игнорить данные
                emitter?.onComplete()//работа завершена
            }
        },BackpressureStrategy.BUFFER)//теперь при создании есть backpressure и создавать свой безопасно(данные будут ждать обработки)

    }


//SUBJECT - разделился на Subject - без backpressure(просит элементы у отправителя ) и Processor с backpressure
}
//Action1 = Consumer , Action0 = Action
//вместо CompositeSubscribe = CompositeDispose (3 урок)
    /*
    Action0 -> Action
    Action1 -> Consumer
    Action2 -> BiConsumer
    ActionN -> Consumer<Object[]>
    Action3 - Action9 -> удалены

    Func0 -> Callable
    Func1 -> Function
    Func2 -> BiFunction
    Func3 - Func9 -> Function3 - Function9*/
