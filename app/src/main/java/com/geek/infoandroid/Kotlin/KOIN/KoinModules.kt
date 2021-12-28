package com.geek.infoandroid.Kotlin.KOIN

import com.example.testcode.Repository
import com.example.testcode.features.ViewModel1
import com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic.apiRoomFull.MainViewModel
import com.geek.infoandroid.Kotlin.les2.MainFragment
import com.geek.infoandroid.android.Level2.les4.RetrofitInfo.RetrofitModel.Weather
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.get
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.getKoin

val application = module {
    single { Weather() }//предоставили синглтон
    factory { Weather() }//создает новый объект при каждом обращении
    single<Weather>(named("weather")) { Weather() }//квалифаер(если возвращают одинаковую зависимость но с разной реализацией)
    single { Repository(weather = get(qualifier = named("weather"))) }//получаем элемент по квалифанру
    viewModel{//предоставит вью модель сразу с фэктори
        ViewModel1(get())
    }
}
//scope
val mainScreen = module{
    scope (named<MainFragment>()){//здесь скоуп привязывается к мэйн фрагменту
        scoped { }
        viewModel{MainViewModel(get())}
    }
    scope(named("my scope")){//здесь скоупом управляем сами(создаем и уничтожаем )
        scoped{}
        viewModel{MainViewModel(get())}
    }

class Repository(weather: Weather){}}