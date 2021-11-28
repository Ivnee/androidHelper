package com.example.testrxjava

import rx.Observable
import rx.Observer
import rx.functions.Func1
import rx.functions.Func2
import java.util.concurrent.TimeUnit

class `8MergeOperators` {
    private val observable1 = Observable.interval(1, TimeUnit.SECONDS)
    private val observable2 = Observable.interval(2, TimeUnit.SECONDS)

    fun work() {
        //mergeWith
        //вариант 1
        observable1.mergeWith(observable2)//совмещаем обсервабл1 с обсервабл2
            .subscribe(object : Observer<Long> { //добавляем ему подписчика
                override fun onCompleted() {}//теперь данные будут приходить от 2х обсерваблов ,и onComplete будет вызван когда оба завершат свою работу
                override fun onError(e: Throwable?) {}
                override fun onNext(t: Long?) {}
            })
        //вариант 2
        Observable.merge(
            listOf(observable1, observable2),
            1//1 обсервабл предоставляет данные в 1 момент времени(обсерваблы скидывают свои данные друг за другом)
        )
            .subscribe(object : Observer<Long> {
                override fun onCompleted() {}//
                override fun onError(e: Throwable?) {}
                override fun onNext(t: Long?) {}
            })
        //Concat с maxConcurrent = 1 ,всегда запускает послелдовательно
        observable1.concatWith(observable2)//запускает обсерваблы последовательно
        //.subscribe()
        Observable.concat(listOf(observable1, observable2))
            .first()//указывает на то что после первого обсервабла,который вернет данные, завершится поток
        //пример
        //Observable.concat(cacheData, dbData, serverData, notFoundData).first()

        //AMB берет несколько Observable и ждет, кто из них первый пришлет данные. Далее, оператор будет возвращать элементы только из этого первого Observable.
        Observable.amb(listOf(observable1, observable2))//будет работать с тем обсервааблом,котоый первым пришлет даннные
            .subscribe()

        //ZIP соединяет данные из двух обсерваблов , и возвращает другой обсервабл(можем вернуть с другим типом данных)
        //вариант 1
        observable1.zipWith(observable2//соединит 2 обсервабла и вернет обсервабл со стрингами
            , object : Func2<Long, Long, String> { //элементы обсервабл которые приходят быстрее ждут элементы обсервабл которые приходят дольше
                override fun call(t1: Long?, t2: Long?): String {//тоесть зип генерирует элементы со скоростью самого медленного обсервабл
                    return "${t1} and ${t2}"
                }
            })
        //вариант 2
        observable1.zipWith(listOf(1L, 2L, 3L, 4L, 5L), object : Func2<Long, Long, String> {//можно сразу вкидывать список элементов
            override fun call(t1: Long?, t2: Long?): String {//тоесть зип генерирует элементы со скоростью самого медленного обсервабл
                return "${t1} and ${t2}"
            }
        })
        //вариант 3
        Observable.zip(observable1,observable2,object:Func2<Long,Long,String>{//можно закинуть сколько укгодно обсервабл,и в конце надо закинуть Func2
            override fun call(t1: Long?, t2: Long?): String ="String"
        }) //вариант 4 можно сделать просто искусственную паузу ,с помощью второго обсервабл,просто задаем Observable.interval(1,TimeUnit.Seconds)

        //CombineLatest -аналог зипа,но только не ждет пока прийдет самый медленный элемент,а кидает данные с тем что есть
        Observable.combineLatest(observable1,observable2,object:Func2<Long,Long,String>{
            override fun call(t1: Long?, t2: Long?): String {//срабатывает каждый раз,как приходит какой-либо элемент
                return "${t1} and %{t2}"//пример вывода из урока 0 and 100 , 1 and 100  ,2 and 200 ,3 and 200, 4 and 300, 5 and 300
            }
        })
        //withLatestFrom аналог зипа, но срабатывает только когда приходят данные из обсервба ,у которого вызвана это функция, а у второго обсервбла берет последнее доступное значение
        observable1.withLatestFrom(observable2,object:Func2<Long,Long,String>{//будет вызываться только когда приходят данные из обсервабл1
            override fun call(t1: Long?, t2: Long?): String ="string"//из обсервабл 2 берет последнее доступное значение
        })
        //flatMap
        val userGroupObservable = Observable.from(arrayOf(UserGroup(),UserGroup(),UserGroup()))//допустим получаем из сети список спиков юзеров
            .flatMap(Func1 <UserGroup,Observable<String>>//по сути мы вытаскиваем из списка ,списки юзеров и вставляем их в обсервабл ,который нам уже выдает потоки этих списков
            {return@Func1  Observable.from(it.users)},1)//ставим 1,чтоб только 1 обсервабл мог выдавать данные и они шли последовательно,иначе все работают одновременно


        //и concatMap - тоже самое что и флэт мап ,но с maxConcurren =1
        val observableConcat = Observable.from(arrayOf(UserGroup(),UserGroup()))
            .concatMap(Func1<UserGroup,Observable<String>> { return@Func1 Observable.from(it.users)//так же выдает каждый элемент списка последовательно,обсерваблы выдают данные поочереди
                .delay(1,TimeUnit.SECONDS) })//так же можно установить паузу с помощью concatMap
    }
    class UserGroup(val users :List<String> = mutableListOf())
}