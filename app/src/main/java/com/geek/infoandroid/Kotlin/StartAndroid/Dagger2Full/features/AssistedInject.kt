package com.example.testcode.features.AssistedInject

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

class NetworkUtils
class MainActivityPresenter @Inject constructor(private val serverApiFactory: ServerApiFactory) {
    //указали что презентр принимает фабрику ApiServer а не сам ApiServer
    var serverApi =
        serverApiFactory.create("myHost","myPort")// и в конце создаем наш Сервер Апи в классе,в коорый передали фабрику
}

class ServerApi @AssistedInject constructor(//аннотация предоставит фабрику для создания этого объекта,в который можно внедрять наши данные
    val networkUtils: NetworkUtils,
    @Assisted("host") val host: String,//помечаем аргумент,который хотим задавать сами//указываем значение в скобках,только если больше одного аргумента нужно пропихнуть вручную
    @Assisted("port") val port: String = "defaultValue"//если нужно 2 аргумента ,которые зададим сами(можно указать дефолтное значение,оно подставится если не указывать порт при создании)
)

@AssistedFactory//помечаем что это фабрика для ассистеда(она должна содедржать метод,возвращающий ServerApi)
interface ServerApiFactory {
    fun create(
        @Assisted("host")host: String,//в фабрике пишем аннотацию Assisted у аргументов,только в случае,если нам
        @Assisted("port")port: String//нужно добавить больше 1 аргумента в ручную
    ): ServerApi//фабрика класса должна в методе возвращать этот класс и принимать свойство,которое хотим задавать сами(ассистед)
}


