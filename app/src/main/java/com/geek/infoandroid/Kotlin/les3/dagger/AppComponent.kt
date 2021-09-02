package com.geek.infoandroid.Kotlin.les3.dagger

import com.geek.infoandroid.Kotlin.les3.dagger.subcomponent.MainSubcomponent
import com.geek.infoandroid.MainActivity
import dagger.Component
import javax.inject.Singleton

//этот компонент живет столько же скольк ои приложение,потому что создается в Application
@Singleton
@Component(modules =[ApplicationModule::class,RepositoryModule::class])//указываем модуль с которым будем работать
interface AppComponent {
    //fun inject(main:MainActivity)//куда мы хотим внедрить наши зависимости(перенесли в сабкомпонент,чтоб инжектился роутер холдер??)
    //теперь сабкомпонент внедряем в сам компонент и он сможет получать доступ ко всему что может провайдить сам компонент
    fun mainSubcomponent():MainSubcomponent.Factory
}