package com.example.testingdagger2

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

//класс с методами которые предоставляют зависимости зависимостей
//испольуется для передачи аргументов в классы - зависимости
@Module
class AutoDependModule (private val depend1: Depend1,private val depend2: Depend2){
    @Singleton//единственный экземпляр(без этой аннотации каждый раз будет создаваться новый экземпляр Depend1)
    @Provides//значит создает зависимость не самостоятельно,а так как мы в модулях указали,находит и подставляет зависимости по типу возвращаемого значения
    fun getDepend1():Depend1 = depend1//можно сконфигурировать здесь любой объект,просто возвращаем Depend1() для простоты кода

    @Singleton//полезно если мы предоставляем классы типа Репозиториев и тд (где бы мы не получили эту зависимость она везде быдет одинаковой)
    //живет пока жив наш компонент
    @Provides//можно вместо этого метода указать так же @Inject внутри класса Depend1||2,если нам не нужны дополнительные аргументы и тд
    @Named("Named1")//для того чтоб различать методы,которые возвращают
    // одинаковые зависимости,просто указываем нэйм в месте где нужно уточнить какую именно зависимость нам надо получить
    fun getDepend2():Depend2 = depend2

    @Provides
    @Named("named2")
    fun getDepend2second():Depend2 {return depend2}
}