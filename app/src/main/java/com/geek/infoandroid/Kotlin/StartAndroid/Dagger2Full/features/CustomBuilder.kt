package com.example.testcode.features

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

class CustomBuilder {
    @Component(modules = [AppModule::class])
    interface AppComponent {
        //создаем свой билдер
        @Component.Builder//билlер,который даггер создаст этому компоненту,будет реализацией данного интерфейса
        interface AppCompBuilder {
            fun buildAppComp(): AppComponent// метод билдера(создает) должен быть без аргументов и возвращать AppComponent
            fun appModule(appModule: AppModule)//метод для получения модуля с зависимостью

            @BindsInstance//метод для передачи контекста
            fun context(context: Context): AppCompBuilder//Теперь Context можно убрать из конструктора AppModule. У компонента будет доступ к этому объекту напрямую, и он сможет передать его в Provides методы для получения SharedPreferences и Resources:
        }

        //Factory создадим замену билдера
        @Component.Factory
        interface AppCompFactory {
            //по сути билдер упакованный в 1 метод ,принимает все BindInstance и модули с зависимостями
            fun create(
                @BindsInstance context: Context,
                appModule: AppModule
            ): AppComponent//как билдер,прокидывает контекст в методы и принимает апп модуль в свой метод
        }
    }


    // в App используем свой билдер для создания компонента
    class App : Application() {
        val appComponent = DaggerAppComponent.//вызываем наш основной компонент
        builder().//у него билдер
        appModule(AppModule(this))
            .//даггер создал этот метод,закидываем модуль и даем ему зависимость(для внедрения зависимостей в класс модуль)
            context(this).//закидываем сразу в методы(BindInstance сразу даст возможность прокинуть контексты в методы Provide)//Более предпочтительный способ
            buildAppComp//у билдера вызываем наш кастомный метод билда

        val appComponentFactory = DaggerAppComponent.factory().//создаем компонент с помощью фэктори
        create(this, AppModule(this))//прокидываем все нужные элементы для компонента и создаем
    }


    //модуль который принимает аргументы
    @Module
    class AppModule(private val context: Context) {
        @Provides
        fun getResources(): Resources {
            return context.resources
        }
    }

    //модуль после @BindsInstance
    @Module
    class AppModule2() {
        @Provides
        fun getResources(context: Context): Resources {
            return context.resources
        }
    }
}