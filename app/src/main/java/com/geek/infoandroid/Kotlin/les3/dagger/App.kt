package com.geek.infoandroid.Kotlin.les3.dagger

import android.app.Application
//нужно добавить а манифест!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
class App:Application() {
    //собираем наш AppComponent у которого сконфигурировалось имя DaggerAppComponent автомотически
    //он будет жить столько же сколько и приложение,потому что создан в апп
    val appComponent = DaggerAppComponent.
    builder().
        //добавляем модуль и прокидываем в него наш аппликейшн,который будем предоставлять всем
    applicationModule(ApplicationModule(this)).
    build()//даггер сам генерит класс даггер апп компонент
}