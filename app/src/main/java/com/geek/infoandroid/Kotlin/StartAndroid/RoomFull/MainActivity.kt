package com.example.testroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val employeeDao = App.database.employeeDao()//получаем наш дао
        val carDao = App.database.carDao()
        employeeDao.getAll()//вызываем нужные методы

        employeeDao.getAllLiveData().observe(this){//получаем даднные в лайфдате
            print(it.get(it.lastIndex).firstName)//получаем данные
        }




//Flowable list
        carDao.getAllFlowable()//subscribeOn ненужен,запрос и так будет выполнен не в UI потоке
            .observeOn(AndroidSchedulers.mainThread())//а вот обсерв он нужен,чтоб вытащить данные
            .subscribe { it.get(1).address }
//Flowable solo
        carDao.getFlowableById(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{it.firstName}//получили фловабл и сразу распечатали данные
//Single
        carDao.getSingleById(1)//single,аналог Flowable, но только 1 раз присылает данные и завершается, либо onSuccess() либо onError()
            .subscribeOn(Schedulers.io())//синглу необходимо задать subscribeOn явно
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({it.firstName}//печатаем данные , есил в single пришел onNext
                ,{it.printStackTrace()})//печатаем ошибку,если в сингле пришео onError
//MayBe
        carDao.getMaybeById(1)//в MayBe может прийти либо onNext либо onComplete , либо onError
            .subscribeOn(Schedulers.io())//требуется явное указание потока работы
            .observeOn(AndroidSchedulers.mainThread())//принимаем в мейн потоке
            .subscribe({it.firstName}
                ,{it.printStackTrace()}
                ,{ print("complete work")})
        /*Flowable подходит, если вы запрашиваете данные и далее планируете автоматически получать их обновления.

        Single и Maybe подходят для однократного получения данных. Разница между ними в том, что Single логичнее использовать,
        если запись должна быть в базе. Если ее нет, вам придет ошибка. А Maybe допускает, что записи может и не быть.*/
    }
}