package com.example.testcode

import dagger.Component
import dagger.Lazy

//даггер сгенерирует класс DaggerAppComponent (Dagger + имя интерфейса), который станет реализацией этого интерфейса.
@Component(modules = [ModuleApp::class,StorageModule::class,NetworkUtils::class,MainModule::class])//модули ,в которых создаются объекты
interface AppComponent {
    //способ 1
    fun getDatabaseHelper():DatabaseHelper//когда даггер сделает реализацию этого интерфейса, он создаст и реализацию этого метода getDatabaseHelper. Он будет в модуле StorageModule, создавать DatabaseHelper и возвращать.
    fun getNetworkUtils():NetworkUtils
    fun getNetworkUtilsLazy():Lazy<NetworkUtils>//лэйзи создает объект только после обращения к нему

    fun getMainActivityPresenter(): MainActivityPresenter//получаем презентр через метод


    //способ2
    fun injectMainActivity(mainActivity: MainActivity)//проверяет какие объекты нужны активити и компонент их создаст для нее

    //получаем сабкомпонент
    fun getMainCompponent():MainComponent
}