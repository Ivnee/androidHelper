package com.geek.infoandroid.Kotlin.StartAndroid.RxJava2

import android.app.Application
import androidx.fragment.app.Fragment
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class EventBus {
    //этот класс можно прокинуть либо через даггер либо через App:Application()
    class Event//какие-то данные

    private val _bus = PublishSubject.create<Event>()//сам сабжект с которым будем работать

    fun post(event: Event) {
        _bus.onNext(event)//метод для отправки данным подписчикам
    }

    fun get(): Observable<Event> = _bus//метод для того чтоб подписаться на получение событий
}

class App : Application() {
    val eventBus = EventBus()
}

val Fragment.app: App //экстеншн для получение App
    get() = requireContext().applicationContext as App
