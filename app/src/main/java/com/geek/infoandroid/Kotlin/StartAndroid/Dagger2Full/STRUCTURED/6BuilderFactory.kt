package com.example.daggertest.`6builder`

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.Resources
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

//1Custome Builder
@Component()
interface AppComponent {
    @Component.Builder//чтобы создать свой кастомный билдер необходимо создать как минимум билд метод
    interface AppCompBuilder {
        fun buildZel(): AppComponent//билд метод, ничего не должен принимать и должен возвращать апп компонент
        fun appModule(appModule: AppModule)//метод для того чтоб прокинуть контекст в апп модуль
    }
}

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(this)//прокинули контекст в апп модуль
            .buildZel()//используем свой билдер для создания компонента

    }
}

@Module
class AppModule(private val context: Context) {

    @Provides
    fun getResources(): Resources {
        return context.resources
    }

}

//2BindInstance
@Component(modules = [AppModule2::class])
interface AppComponent2 {
    @Component.Builder//Указываем что это билдер
    interface AppCompBuilder {
        fun buildAppComp(): AppComponent//метод для создания нашего билдера(вызывается в App)
        @BindsInstance//дает возможность прокидывать объекты без использования модулей
        fun context(context: Context): AppCompBuilder//метод для того чтоб прокинуть контекст в наш компонент(и можно будет использовать контекст в любом модуле)
    }
}

@Module
class AppModule2 {
    //теперь в конструктор модуля не нужно прокидывать контекст чтоб работать с ним,мы его получили через бинд инстанс
    @Provides
    fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("prefs", MODE_PRIVATE)
    }

    @Provides
    fun getResources(context: Context): Resources {
        return context.resources
    }
}

class App2 : Application() {
    val appComponent = DaggerAppComponent
        .builder()
        .context(this)//прокидываем контекст в метод BindInstance
        .buildAppComp()
}

//Factory
@Component
interface AppComponent3 {
    @Component.Factory//Указываем что это Фэктори
    interface AppCompFactory { //имя фэктори может быть любым
        fun create(@BindsInstance context: Context, appModule: AppModule): AppComponent//с помощью бинд инстанс мы предоставим даггеру контекс,который он сможет использовать везде
    }
}

class App3 : Application() {
    val appComponent = DaggerAppComponent
        .factory()
        .create(this, AppModule(this))//прокинули контекст и прокинули АппМодуль с зависимостью
}