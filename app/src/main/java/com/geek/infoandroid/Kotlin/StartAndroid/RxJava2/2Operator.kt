package com.example.testrxjava

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.core.Observable
import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.functions.Func1
import rx.functions.Func2
import rx.schedulers.Schedulers
import java.util.Date.from
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit


class Observable2 : AppCompatActivity() {
    //observable
    //FROM
    val observable = Observable.fromArray(
        arrayOf("one", "two", "three")
    )//создает поток данных из аррай листа
    val observable2 = Observable.fromIterable(//создает поток из элементов листа
        listOf(1,2,3,4)
    )

    //RANGE
    val observableRange =
        Observable.range(10, 3)//начать надо с 10 ,количесвто элементов = 3(т.е выведется 10 11 12)

    //INTERVAL
    val observableInterval = Observable.interval(
        500, TimeUnit.MICROSECONDS
    )//каждые 500 м сек будет приходить лонг начиная с 0 1 2 3 4 ....

    //FromCallable
    val fromCallObservable =
        Observable.fromCallable(CallableLongAction("5"))//вызываем у калобл класс обернутый в коллабл ,в который вызываем блокирующий метод
            .subscribeOn(Schedulers.io())//выбираем поток,в котором будут создаваться данные и вызываться метод Call
            .observeOn(AndroidSchedulers.mainThread())//поток в котором будет обсервер полуать данные,

    //observer
    val observer = object : Observer<String> {
        override fun onCompleted() {
        }

        override fun onError(e: Throwable?) {
        }

        override fun onNext(t: String?) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        observable.subscribe(observer)//добавляем отправиьелю событей нашего наблюдателя
    }
}

//fromCallable
class CallableLongAction(private val data: String) : Callable<Int> {
    //создаем класс  для Observable.fromCallable
    override fun call(): Int {//переопределяем метод колл
        return longAction(data)//и вызываем в нем какой-то блокирующий код
    }

    private fun longAction(text: String): Int {
        TimeUnit.SECONDS.sleep(1)//блокирующий код
        return text.toInt()
    }
}

//Map
private val func1 = object : Func1<String, Int> {
    //парсер класс
    override fun call(t: String?): Int {
        return Integer.parseInt(t)
    }
}
private val mapObservable = Observable.fromArray(arrayOf("1", "2", "3"))
    .map(func1)//устанавливаем мап,в котором будет функция ттрансформирующая наши данные

//и получаем его в Observer onNext//если невозможно будет преобразовать ,то ошибка прилетит в onError и последовательность завершится
//BUFFER
private val bufferObservable = Observable
    .fromIterable(listOf(1, 2, 3, 4, 5, 6, 7, 8))
    .buffer(3)//Observable будет кидать в обсервер по 3 элемента в виде List<Int> ([1,2,3])

//TAKE
private val takeObservable = Observable.fromIterable(listOf(1, 2, 3, 4, 5, 6, 7))
    .take(3)//отправлять будет только первые 3 элемента (по 1 шт)
//SKIP
private val skipObservable = Observable.fromIterable(listOf(1, 2, 3, 4, 5, 6, 7))
    .skip(2)//первые 2 элемента пропустит и обсервер начнет получать с 3

//DISTINCT

private val distinctObservable = Observable.fromIterable(listOf(1,1,1,1, 2, 3, 3, 3, 3, 3))
    .distinct()//уберет все элементы,которые повторяются , выведет 1,2,3
//Filter
private val filterFunc1 = object:Func1<String,Boolean>{//Фильтр,который возвращает тру,если в элементе есть 5
    override fun call(s: String): Boolean {
        return s.contains("5")
    }
}
private val filterObservable = Observable.fromIterable(listOf("1","15","123123"))
    .filter(filterFunc1)//установили фильтр для отправляемых данных, теперь мы получим только значения в которых есть "5"

//MERGE
private val mergeObservable = Observable.fromIterable(listOf(1,2))
    .mergeWith(Observable.fromIterable(listOf(3,4,5)))//мердж принимает в себя еще 1 обсервабл,который добавится к нашему и обсервер получит 1 2 3 4 5
//ZIP
private val func2Zip = object: Func2<Int,String,String>{//int-тип обсервабл номер 1, String- тип обервабл 2 String-какой тип возращаем
    override fun call(t1: Int, t2: String): String {
        return "${t1}: " +t2//функция которая возвращает соедененные данне
    }
}
private val zipObservable = Observable.from(listOf(1,2,3))
    .zipWith(Observable.from(listOf("one","two","three")), func2Zip)//склеиваем обсерваблы , указываем обсервабл и метод кслеивания
//на выходе будет one:1 two:2 three:3

//TAKE UNTIL
private val takeUntilFunc1 = object: Func1<Int,Boolean>{//вернет тру на элементе 5
    override fun call(t: Int): Boolean {
        return t == 5
    }
}
private val observableTakeUntil = Observable.from(listOf(1,2,3,4,5,6,7))
    .takeUntil(takeUntilFunc1)//будет выводить данные до определенного момента(пока не найдет 5)1 2 3 4 5(5 включительно)
//ALL
private val allFunc1 = object:Func1<Int,Boolean>{
    override fun call(i: Int): Boolean {
        return i<10
    }
}
private val observableAll= Observable.from(listOf(1,2,3,4,5,6,7,8))
    .all(allFunc1)//проверит все ли элементы < 10 , если да,вернет просто true(1 элемент)

//ACTION
//используется если нам нужно только отлавливать события,без комплита и эррора
private val actionObservable = Observable.from(listOf(1,2,3))
private val action = object: Action1<Int>{
    override fun call(t: Int) {
        print(t)
    }
}
fun subscribeObserver123(){
    actionObservable.subscribe(action)
}

