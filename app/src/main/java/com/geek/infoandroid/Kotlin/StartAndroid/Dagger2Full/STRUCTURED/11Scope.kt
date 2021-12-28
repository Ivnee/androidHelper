package com.example.daggertest.`11Scope`

import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Inject
import javax.inject.Scope


@Module
class OrderModule(){}
//код в Activity для  получения репозитория
//   val orderComponent = (application as App).appComponent.getOrderComponent()
//   val orderRepository = orderComponent.getOrderRepository()


//1)ЧЕРЕЗ INJECT ПОЛУЧАЕМ СИНГЛТОНЫ
//1.1)Scope с жизненным циклом Subcomponent
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class OrderScope //название должно описывать время жизненного цикла скоупа

@OrderScope//этой аннотацией включили проверку создаваемых объектов на аннотацию OrderScope,если она есть,то это синглтон объект
@Subcomponent(modules = [OrderModule::class])
interface OrderComponent {
    fun getOrderRepository(): OrderRepository//если у репозитория OrderScope - он живет как сабкомпонент, если AppScope-как компонент
}
@OrderScope//сабкомпонент увидит такую же аннотацию и создаст нам синглтон репозиторий
class OrderRepository @Inject constructor() {//инжект прокидывает этот объект без модуля,компонент использует его напрямую
}
//1.2)Scope с жизненным циклом Component
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope //создаем аннотацию для Компонента

@AppScope//добавляем эту аннотацию к основному компоненту
@Component(modules = [StorageModule::class, NetworkModule::class])
interface AppComponent{
    fun getOrderRepository():OrderRepository2//если добавлена аннотация @AppScope у компонента и у репозитория,то создаст синглтон
    //Лучше репозиторий ордер в сабкомпоненте ордер,он будет имень жизненные цикл AppScope,так как сабкомпонент проверит аннотацию
    //найдет там аппскоуп и попросить создать репозиторий у Компонента ,соответственно жизненный цикл будет как у этого компонента
}

@AppScope//помечаем той же аннотацией что и компонент
class OrderRepository2 @Inject constructor() {//инжект прокидывает этот объект без модуля,компонент использует его напрямую
}

//2)если объект создается в модуле (@Provides)

@Module
class OrderModule2 {
    @OrderScope//теперь этот модуль может быть использован только в компонентн/сабкомпоненте с такой же аннотацией (Singltone)
    @Provides//если поменяем на AppScope ,то этот модуль обязательно должен лежать в Component/Subcomponent с таким же скопом
    fun provideOrderRepository(): OrderRepository {
        return OrderRepository()
    }
}
@OrderScope
@Subcomponent(modules = [OrderModule::class])//добавили модуль
interface OrderComponent2 {
    fun getOrderRepository(): OrderRepository

}





////////////////////////
@Module
class StorageModule {}

@Module
class NetworkModule {}
