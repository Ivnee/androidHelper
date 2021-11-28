package com.example.testcode.operators

import com.example.testcode.MainActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Named

class Named123 {
    //Named
    class SomeApi(private val url: String) {}//какой-то апи который прнимает урл

    @Module
    class ApiModule {
        @Named("main")//узазываем ключ,по которому будем выбирать нужную нам реализацию
        @Provides
        fun provideSomeApiMain(): SomeApi {//метод с сервером мейт
            return SomeApi("main.server.ru")
        }

        @Named("demo")//узазываем ключ,по которому будем выбирать нужную нам реализацию
        @Provides
        fun provideSomeApiDemo(): SomeApi {//метод с сервером демо
            return SomeApi("demo.server.ru")
        }
    }
//Если используем гет метод
    @Component(modules = [ApiModule::class])
    interface appComponent{
        @Named("main")
        fun getSomeApiMain(): SomeApi
    }
    //MainActivity
    lateinit var someApi: SomeApi
    //someApi = (application as App).appComponent.getSomeApiMain()
//Если используем инжект
    //component interface
    fun injectMainActivity(mainActivity: MainActivity){}
    //MainActivity
    @Inject
    @Named("main")
    lateinit var someApiMain: SomeApi

}