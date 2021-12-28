package com.example.daggertest.`8subcomponentSetObject`

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.daggertest.R
import dagger.*
import javax.inject.Inject

class App: Application(){
    val appComponent = DaggerAppComponent().create()
}

//1) стандартный способ передичи объектов (Нельзя использовать бинд инстанс)
@Component(modules = [StorageModule::class, NetworkModule::class])
interface AppComponent {
    fun getMainComponent(mainModule: MainModule): MainComponent//метод для поолучения сабкомпонента,в который передаем нужные зависимости
}
@Module
class MainModule(private val context: Context) {//модуль ,которому требуется контекст для создания каки-то объектов
    @Provides
    fun provideContext():Context = context//теперь в любом модуле сабкомпонента доступен контекст
}

@Subcomponent(modules = [MainModule::class])
interface MainComponent {//сабкомпонент
}

class MainActivity : AppCompatActivity() {
    val appComponent = (application as App).appComponent
    val mainComponent = appComponent.getMainComponent(MainModule(this))// как получить сабкомпонент в мейн активити
}

//2) способ через билдер
@Subcomponent(modules = [MainModule::class])
interface MainComponent2 {//в сабкомпоненте прокидываем зависимости через билдер

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: Activity): Builder//указываем какой объект нужно нам получать
        fun build(): MainComponent//указываем метод сборки сабкомпонента
    }

    fun getMainActivityPresenter(): MainActivityPresenter
}
@Component(modules = [StorageModule::class, NetworkModule::class])
interface AppComponent2 {//как получить сабкомпонент с билдером в компоненте->
    fun getMainComponentBuilder(): MainComponent2.Builder//получаем билдер сабкомпонента

}
class MainActivity2 : AppCompatActivity() {//и как получить этот сабкомпонент в активити
    val appComponent = (application as App).appComponent
    val mainComponent = appComponent.getMainComponentBuilder()//получаем билдер сабкомпонента
        .activity(this)//прокидываем в метод активити нашу активти(это тот метод который мы создали сами в сабкомпонентн)
        .build()//тоже наш метод,создаем нам сабкомпонент
}
//3)Factory
@Subcomponent(modules = [MainModule::class])
interface MainComponent3 {

    @Subcomponent.Factory
    interface Factory {//создаем фабрику
        fun create(@BindsInstance activity: Activity): MainComponent3//метод в который будем прокидывать зависимости(сразу бинд инстанс,чтоб не кидать вместе с модулем)
    }
    fun getMainActivityPresenter(): MainActivityPresenter

    }

@Component(modules = [StorageModule::class, NetworkModule::class])
interface AppComponent3 {
    fun getMainComponentFactory(): MainComponent3.Factory//в обычном компоненте возвращаем фабрику нашего сабкомпонента

    class MainActivity2 : AppCompatActivity() {
        val appComponent = (application as App).appComponent
        val mainComponent = appComponent.getMainComponentFactory().create(this)//и получаем сабкомпонент с прокинутым в фабрику активити
    }
}
//4)Inject

@Component(modules = [StorageModule::class, NetworkModule::class,AppModule::class])
interface AppComponent4 {
    fun injectMainActivity(activity: MainActivity3)//Инжект мейн активити
}
class MainActivity3 : AppCompatActivity() {

    @Inject
    lateinit var mainComponentBuilder: MainComponent2.Builder//сюда нам прокинется билдер сабкомпонента после инжекта

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).appComponent.injectMainActivity(this)//инжектим активити в компонент
        val mainComponent = mainComponentBuilder.activity(this).build()//и собираем из билдера сабкомпонент
    }
}
@Module(subcomponents = [MainComponent::class])//чтоб работал инжект,обязательно нужно добавить наш сабкомпонент в любой модуль компонента
class AppModule {}//а сам модуль должен быть добавлен в @Component()










///////////////////////////

@Module
class StorageModule {}

@Module
class NetworkModule {}

class MainActivityPresenter(databaseHelper: DatabaseHelper, networkModule: NetworkModule) {}
class DatabaseHelper {}
class NetworkUtils {}