package com.example.daggertest.`13AssistedInject`

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

//Ассистед инжект нужен для передачи объектов  типа String во время работы(без компонента)
//1)
class ServerApi @AssistedInject constructor(//AssistedInject используем вместо обычного Inject
    val networkUtils: NetworkUtils,
    @Assisted val host: String//помечаем тот объект,который хотим задавать самы во время создания
)//для создания нужен объект и строка

@AssistedFactory//Фабрика для создания ServerApi(нужна для ассистед инжекта)
interface ServerApiFactory {
    fun create(host: String): ServerApi//обязана содержать метод,который возвращает сервер апи,а на вход принимает все что помечено Assisted
}
//чтоб получить презентер с таким сервер апи в конструкторе нужно получать не ServerApi ,а ServerApiFactory
class MainActivityPresenter @Inject constructor(private val serverApiFactory: ServerApiFactory){
    val serverApi = serverApiFactory.create("this host")//теперь используем фабрику чтоб создать нужный сервер апи
}
//2)если нужно прокинуть несколько объектов одного типа (String) с аннотацией @Assisted
class ServerApi2 @AssistedInject constructor(//инжектим данные
    val networkUtils: NetworkUtils,//сам создает нетворкутилс
    @Assisted("host") val host: String,//явно указываем имено у аннотации ассистед,чтоб даггер смог отличить данные с одим типом
    @Assisted("port") val port: String)

@AssistedFactory//создаем фабрику
interface ServerApiFactory2 {
    fun create(
        @Assisted("host") host: String,//такие же имена используем в фабрике
        @Assisted("port") port: String = "228"//значение по умолчани.
    ): ServerApi
}

class MainActivityPresenter2 @Inject constructor(private val serverApiFactory2:ServerApiFactory2){//прокидываем с помощью инжект фабрику
    val serverApi = serverApiFactory2.create("www.ru","90")//и создаем наш объект из фэктори
}



class NetworkUtils {}