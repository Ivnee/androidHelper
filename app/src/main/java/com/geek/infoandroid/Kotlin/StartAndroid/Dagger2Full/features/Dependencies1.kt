package com.example.testcode.features

import android.app.Application
import com.example.testcode.MainActivityPresenter
import com.example.testcode.NetworkModule
import com.example.testcode.StorageModule
import dagger.Component
//ВАЖНО в dependencies нет доступа к модулям AppComponent1, можно получить обекты ,только если они предоставляются в компоненте методами get
@Component(modules = [StorageModule::class, NetworkModule::class])
interface AppComponent1{}

//Депенденсиес указывают что мэйн компонент должен иметь доступ к объектам АппКомпонента
@Component(modules = [CustomBuilder.AppModule::class],dependencies = [AppComponent1::class])//депенденсис создаст метод куда надо будет вкинуть наш апп компонент
interface MainComponent1{
    fun getMainActivityPresenter():MainActivityPresenter
}
class App1:Application(){
    val appComponent1 = DaggerAppComponent1.create()
    val mainComponent = DaggerMainComponent.builder().
            appComponent1(appComponent1).//теперь для инициализации мэйн компонента нужно прокинуть апп компонент,чтоб можно было пользоваться его объектами
            build()
}