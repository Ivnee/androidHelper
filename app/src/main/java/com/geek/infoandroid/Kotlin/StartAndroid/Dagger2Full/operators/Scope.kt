package com.example.testcode.operators

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.example.testcode.App
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Inject
import javax.inject.Scope


//активити
class MainActivity:AppCompatActivity(){}
class OrderActivity :AppCompatActivity(){
    val orderComponent = (application as App).appComponent.getOrderComponent()//создаем сабкомпонент
    val repository = orderComponent.getOrderRepository()//берем у него репозиторий
}
//Repositoroy(ЕСЛИ ПОЛУЧАЕМ КЛАСС ЧЕРЕЗ ИНЖЕКТ)
@AppScope//если добавили апп скоуп ,то репозиторий живет даже после уничтожения ордер активити,или если..
@OrderScope//ордер скоуп ,то репозиторий умрет после закрытия ордер активити и по открытию создаст новый
class OrderRepository @Inject constructor(){}

//component
@AppScope//скоуп синглтона,синглтон будет жить столько же сколько и компонент(пока приложение работает)
@Component
interface AppComponent{
    fun getOrderComponent():OrderComponent
}
//включаем режим создания синглтонов у компонента
@OrderScope//если какой-то класс помечен этой же аннотацией , то компонент(или сабкомпонент)делаем из него синглтон
@Subcomponent(modules = [OrderModule::class])
interface OrderComponent{
//метод в сабкомпоненте,но жизненный цикл AppScope.Сабкомпонент не может создать такой ,поэтому просит у родителя,у родителя AppScope и у repository AppScope
    fun getOrderRepository():OrderRepository//поэтому апп компонент и создаст его(в случае OrderScope ,саб просто создает его)
}
//Module
@Module//если мы будем делать синглтон через Модуль
class OrderModule(){
    @OrderScope//Этот модуль может быть использован только в компоненте/сабкомпоненте с такой же scope аннотацией(OrderScope)
    @Provides
    fun provideOrderRepository():OrderRepository = OrderRepository()
}

//Скоуп аннотации
@Scope//аннотации со скоупом делают из объектов синглтоны
@Retention(AnnotationRetention.RUNTIME)
annotation class OrderScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope
