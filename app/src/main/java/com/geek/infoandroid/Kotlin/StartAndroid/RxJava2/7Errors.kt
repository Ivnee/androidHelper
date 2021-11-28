package com.example.testrxjava

import rx.Observable
import rx.Observer
import rx.functions.Action1
import rx.functions.Func1
import rx.functions.Func2

class `7Errors` {
    private val stringObservable =
        Observable.just("1", "AA", "3", "4", "5")//если не обработать AA,вылетит ошибка

    private val observable = stringObservable.map(object : Func1<String, Long> {
        override fun call(t: String): Long {
            return t.toLong()
        }
    })

    fun work() {
        //способ обработки в onError
        observable.subscribe(object : Observer<Long> {
            override fun onCompleted() {}
            override fun onError(e: Throwable?) {//после вызова он еррор обсервабл прекращает отправку данных
                print(e?.message)//обработали ошибку ,приложение не крашнулось
            }

            override fun onNext(t: Long?) {}
        })
//эти обработчики ловят ошибку, и она более не попадает в onError. А значит мы снова можем использовать Action, вместо полноценного Observer.
        //onErrorReturn - вместо ошибки отправляет значение
        val observable2 = stringObservable
            .map(object : Func1<String, Long> {
                override fun call(t: String): Long {
                    return t.toLong()
                }
            })
            .onErrorReturn(object : Func1<Throwable,Long>{
                override fun call(t: Throwable?): Long {
                    return 228L//в случае ошибки подписчикам прилетит 228 и отправка остановится
                }
            })

        //onErrorResumeNext - позваляем вместо ошибки отправлять последовательность в виде обсервабл
        val observable3 = stringObservable
            .map(object : Func1<String, Long> {
                override fun call(t: String): Long {
                    return t.toLong()
                }
            })
            .onErrorResumeNext(Observable.just(2L,2L,8L))//теперь после ошибки нам прийдет 2 2 8 и поток завершится
            .onErrorResumeNext(object:Func1<Throwable,Observable<Long>>{//тоже самое,если нужно отловить конкретные ошибки
                override fun call(t: Throwable?): Observable<Long> {
                    if(t is Throwable) return Observable.just(1,2,3,4,5)
                    return Observable.just(1,2,3,4,5)
                }
            })
    }
    //onExceptionResumeNext
    //Этот оператор аналогичен оператору onErrorResumeNext, но не поймает ошибки Throwable и Error, только Exception.

    //Retry в случае ошибки обсервер подпишется по новой и попробует еще раз  получить данные

    private val observable5 = stringObservable
        //.map()//здесь будет ошибка
        //.retry(2)//сделает 2 попытки,если не указать число попыток,будет вечно пытаться
        .retry(object: Func2<Int, Throwable,Boolean>{//либо можно установить ретрай при каких=то условиях
            override fun call(retryCount: Int, t2: Throwable?): Boolean {
                return retryCount<3//здесь ради примера указали если попыток меньше 3,то пробовать еще
            }
        })
    //retryWhen.......
}