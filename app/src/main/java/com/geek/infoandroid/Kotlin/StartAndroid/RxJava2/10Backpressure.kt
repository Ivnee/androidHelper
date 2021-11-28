package com.example.testrxjava

import rx.Observable
import rx.Observer
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit
// ВО ВТОРОЙ ВЕРСИИ Flowable ведет себя точно так же как обсервер здесь,а обсервер теперь не имеет onBackPresure
//onBackPressure - возможность обсервара запросить данные у отправителя,когда буфер был освобожден
class `10Backpressure` {
    fun work(){
        //в данном случае Observale ждет когда Observer получит данные, чтобы отправить их
        Observable.range(1,10)//range поддерживает оператор backpressure
            .subscribeOn(Schedulers.computation())
            .doOnNext{ print("observable отправил ${it}")}//под лямбдой Action1(срабатывает каждый раз ,как Observable.range пошлет нам данные)
            .subscribe(object :Observer<Int>{
                override fun onCompleted() {}
                override fun onError(e: Throwable?) {}
                override fun onNext(t: Int?) {
                    print("onNext ${t}")//выводит значение которое пришло от обсервабл
                }
            })
        //Теперь данные отправляются в computation потоке, а принимаются в io потоке.и больше ненужно ждать получателя
        Observable.range(1,10)
            .subscribeOn(Schedulers.computation())
            .doOnNext{ print("observable отправил ${it}")}
            .observeOn(Schedulers.io(),20)//у этого метода есть буфер,который хранит данные ,пока получатель их
            .subscribe(object :Observer<Int>{// не примет(дефолтный размер 16 ), через запятую можно указать размер буфера,Когда он переполнится,отправителю прийдется ждать освобождения буфера
                override fun onCompleted() {}
                override fun onError(e: Throwable?) {}
                override fun onNext(t: Int?) {
                    print("onNext ${t}")
                }
            })
        //если использовать interval вместо рендж ,при заполнении буфера обсервабл не будет ждать,а продолжит постить данные с указанным интервалом
        //в результате это приведет к rx.exceptions.MissingBackpressureException
//какие бывают onBackpressure
//свой Observable
        val customeObservable = Observable.OnSubscribe<Int> {
            var i = 1//просто кидает данные от 1 до 20 ,игнорирую onBackPressure
            while (i <= 20) {
                if (it.isUnsubscribed()) return@OnSubscribe
                it.onNext(i++)
            }
            it.onCompleted() }

        Observable.create(customeObservable)
            .subscribeOn(Schedulers.computation())
            .doOnNext{ print("toBuffer ${it}")}
            .onBackpressureBuffer()//сохраняет в себя данные от обсервабл и давет мовможность достать из получателю(тоесть backpressure,используется если нет поддержки backpressure)
            //то добавляем этот метод ,и он будет в себе хранить данные, до освобождения буфера в observeOn(может забиться и вызвать)out of memory
            .onBackpressureDrop()//просто выбросит данные ,если их нельзя запихнуть в буфер observeOn
            .onBackpressureDrop{print(it)}//здесб мы получаем все элементы,которые были выкинуты из- за переполнения буфера(под лямбдой Action1)
            .onBackpressureLatest()//так же как и дроп,но когда обсервер будет готов получать элементы ,latest отдаст последний,он всегда сохраняет последний полученный элемент
            .doOnNext({ print("fromBuffer ${it}")})
            .observeOn(Schedulers.io())
            .subscribe(object :Observer<Int>{
                override fun onCompleted() {}
                override fun onError(e: Throwable?) {}
                override fun onNext(t: Int?) {
                    TimeUnit.MILLISECONDS.sleep(100)
                    print("onNext ${t}")
                }
            })


    }
}