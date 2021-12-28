package com.example.daggertest.`5component`

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.Resources
import dagger.Component
import dagger.Module
import dagger.Provides

class App:Application(){
    lateinit var appComponent:AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent =DaggerAppComponent
            .builder()//апп модуль метод создастся автоматически,после того,как создадим конструктор
            .appModule(AppModule(this))//чтоб прокинуть в модуль какие-нибудь зависимости,нужно создать этот модуль через билдер
            .build()
    }
}
@Component(modules = [AppModule::class])
interface AppComponent{}


@Module//модуль который работает с контекстом
class AppModule(private val context: Context) {//для использования контекста передаем его в сам модуль в виде аргумента
    @Provides//обязательно нужно вернуть сам контекст,чтоб использовать его вне модуля
    fun getContext():Context = context//теперь можно получить в других модулях контекст
    @Provides
    fun getPreferences(): SharedPreferences {
        return context.getSharedPreferences("prefs", MODE_PRIVATE)
    }
    @Provides
    fun getResources(): Resources {
        return context.resources
    }
}