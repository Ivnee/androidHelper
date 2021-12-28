package com.geek.infoandroid.Kotlin.StartAndroid.RxJava2

import androidx.appcompat.app.AppCompatActivity
import com.geek.infoandroid.R
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.*
import java.util.concurrent.TimeUnit

class All:AppCompatActivity(R.layout.main_activity2){
    fun work() {
        val observable = Observable.just("1", "2", "3")//отправляет данные пачками Next,Error,Complete
        val flowable = Flowable.create<String>({ for (i in 1..5){it.onNext(i.toString())}},BackpressureStrategy.BUFFER)//тоже самое ,имеет еще backpressure
        val single = Single.just("1")//отправляет 1 элемент только ,Success, Error
        val maybe = Maybe.just(1)//возвращает или результат или completable, Completable,Error,Success
        val completable = Completable.create({})//по завершению просто вызывает либо Error или Complete

        val dispose = observable//диспос используется для отмены
            .subscribeOn(Schedulers.io())//в каком потоке будет выполняться код получения данных
            .observeOn(AndroidSchedulers.mainThread())//в каком потоке выполняется subscribe{}
            .subscribe{}

        val cd = CompositeDisposable()//сюда добавляем сколько угодно диспособлов
        cd.add(dispose)//метод добавления
        cd.clear()//отключили отправку данных у всех добавленых диспосаблов(обычно вызывается в onDestroy)
        dispose.dispose()//отключили отправку данных

 //Операторы создания
        Observable.create(ObservableOnSubscribe<Any> { TODO("Not yet implemented") })//создаем свой
        Observable.just("1","2")//выполняется 1 раз и потом всем подписчикам присылает этот результат,в любой промежуток времени
        Observable.defer( { Observable.just(1)})//обернутые в дефе будут вычислять значение после каждой подписки
        Observable.fromArray(listOf(1,2,3,4,5,6))//поочередно отправит данные из массива
        Observable.range(0,20)//поочередно отправит от 0 до 20
        Observable.fromCallable({ println("123")})//создает источник из метода
        Observable.interval(1,TimeUnit.SECONDS)//бесконечный источник лонгов от 0 до бесконечности(только указать интервал)
        Observable.timer(1,TimeUnit.SECONDS)//через заданное время выдаст 0L
        //Observable.generate()???

 //Операторы трансформации
        flowable
            .map { it+"данные" }//трансформирует данные испускаемые источником
                //it в эти методы прилетает столько же раз ,сколько элементов было в флоубл(как и в мам)
            .flatMap { Flowable.just(it + "данные") }//данные приходят не по порядку,Возвращает новый источник данных
            .switchMap { Flowable.just(it).delay(1,TimeUnit.SECONDS) }//пропускает  данные ,если они не обработались до прихода другого источника
            .concatMap { Flowable.just(it +"1") }//сохраняет последовательность,не прийдет к след элементу пока полностью не завершит предыдущий
            .scan{t1,t2->
               return@scan t1 + " " + t2//t1 это предыдущий результа,т t2 последний пришедший элемент(1,12,123,1234)
            }
            .groupBy { it.contains("1")}//может вернуть любые типы данных , теперь доступно в subscribe через it.key(true или false в данном случае)
            .buffer(2)//если данные "а" "б" "в" "г" "д" в сабскрайб будет приходить лист с "а" "б" , лист с "в"  "г", лист с д

 //операторы фильтрации
        val dispose2 = observable
            .debounce(1,TimeUnit.SECONDS)//испускает данные только спустя секунду,если между отправкой данных меньше секунды,пропускает
            .distinct()//удаляет все одинаковые элементы,для классов (которые не data)нужны методы hashcode и equals
            .filter { it.contains("1") }//если тру,до пройдет элемент,если фолс ,не заэмитит(можно 2 и более фильтров)
            .sample(2,TimeUnit.SECONDS)//каждые 2 сек будет отправлять последний испущенный элемент
            .skip(2)//пропустит первые 2 элемента
            .skipLast(1)//пропустит последний элемент
            .take(1)//отправит только первый ээлемент
            .takeLast(2)//отправит 2 последних элемента
            .elementAt(3)//возьмет только 3ий элемент в массиве
            //.ignoreElements()//все элементы не пропустит, метод он некст уберется ,если нужен только сам факт выполнения

 //операторы комбинирования
        val observable1 = Observable.just(1,2)
        val observable2 = Observable.just("1","2")
            //zip
        val disp = observable1.zipWith(observable2, { t1, t2 -> t1.toString() + t2 })//BiFunction объединяет 2 источника данных(ждет выполнения всех обсервабл,завершится когда завершится первый обсервабл)
        val disp2 = Observable.zip(observable1,observable2,//тоже самое, по другому вызывается(можно осуществлять любые трансформации данных )
            { t1, t2 -> t1.toString() + t2})//object:BiFunctions<Int,String,String>{}
                //merge
            .mergeWith(observable.zipWith(Observable.interval(1,TimeUnit.SECONDS),{ o1, o2 -> o1+o2}))//или merge(объединяет 2 обсервабла и отправляет их данные в он некст(не соблюдает очередности))видимо данные должны быть одного типа

            //combineLatest
        val disp3 = Observable.combineLatest(observable1,observable2,
            { t1, t2 -> t1.toString() + t2 })
            .subscribe({ it},{})//будет вызываться каждый раз как какой-то из обсерваблов испускает данные,берет последние данные
            //concat
        Observable.concat(observable1,observable2)//сначала приходят все данные от первого,потом от второго(очередность соблюдается)
            .subscribe()
            //switchOnNext
        //val disp4 = Observable.switchOnNext()//как только приходит второй поток данных,он отключается от первого()

//другие
            //all
        val disp5 = observable.all({it.contains("1")}).subscribe({},{})//если все элементы содержат "1",то в success прийдет тру(all делает из осервабл сингл)
            //contains
        val disp6 = observable1.contains(3).subscribe({},{})//если есть 3 в обсервабл,то вернет тру
            //delay
        val disp7 = observable1.delay(1,TimeUnit.SECONDS).subscribe({},{})//начнет испускать данные через 3 сек
            //count
        val disp8 = observable2.count().subscribe({},{})//делает сингл,вернет количество элементов
            //defaultIfEmpty
        val dips9 = observable.defaultIfEmpty("not Found").subscribe({},{})//если пустой элемент, то отправит not found
            //timeInterval
        val disp10 = observable.timeInterval().subscribe { it.time().toString() + it.value() }//получаем значение интервала времени между элементами
            //timeout
        val disp11 = observable1.timeout(300,TimeUnit.MILLISECONDS).subscribe{}//если данные не будут приходить 300 милисек,прийдет ошибка в он еррор
        //timestamp
        val disp12 = observable.timestamp()//в он некст прийдет элемент который содержит время(когда эмит был) и value


        observable
            .doOnNext{}//сначала срабатывает doOnNext, а потом onNext()
            .doOnSubscribe{}//выщывается когда на него подписываются
            .doOnComplete{}
            .doOnError{}
            .subscribe()
//coldHot
        val connectObservable = Observable.interval(1,TimeUnit.SECONDS)
            .publish()//создает хот обсервер ,чтоб начал испускать данные нужно вызвать .connect()
            .replay()//тоже хот,но вновь прибывшие подписчики получат все данные
            .refCount()//использовать либо с публишь либо с реплай,когда есть хоть 1 подписчик ,испускает данные ,когда нет подписчиков,останавливается, и при появлении начинает отправку заного

//Errors
        observable
            .doOnError{ print("какое-то действие при ошибке")}
            .onErrorReturn{"asd"}//при ошибке вернет этот элемент(стринг потому что обервабл эмитит стринги)
            .onErrorResumeNext ( Observable.just("d","c","e"))//при ошибке продолжит последовательность элементов этим обсерваблом
            .onExceptionResumeNext(Observable.just("a","2","3"))//тоже самое,но работает только на Exception,Error и Throwable не поймает
            .retry()//при ошибке будет повторно подписываться на данные
            .retry(3)//3 раза попробует получить данные и все
            //.retryWhen {}
            .repeatUntil { true == true}//повторять пока не выполнится условие
            .subscribe()
//PublishSubject
        val publishSubject = PublishSubject.create<String>()//сабжект подписывается на обсервабл,а на сабжект подписываются обсерверы
        observable.subscribe(publishSubject)//отправка данных начинается когда сабжект подписался на обсервабл
        publishSubject.subscribe({},{},{})
        val replaySubject = ReplaySubject.create<String>()//тоже самое ,но с буфером(можно указать размер)
        val behaviorSubject = BehaviorSubject.create<String>()//размер буфера = 1 и можно указать с какого элемента начинаем
        val asyncSubject = AsyncSubject.create<String>()//будет испускать только последнее значение(когда последовательность завершена он выдает последний)
        val unicastSubject = UnicastSubject.create<String>()//может подписаться только один обсервер,даже после отписки нельзя подписаться другим


//RxBinding
        //источник данных из EditText
        val rxDispose =  RxTextView.textChanges(findViewById(R.id.edit_text))
            .debounce(500,TimeUnit.MILLISECONDS)
            .subscribe({ print(it)},{})


    }
}