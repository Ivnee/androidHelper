package com.example.daggertest.`3lazy`

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.daggertest.R
import com.geek.infoandroid.R
import dagger.*
import dagger.multibindings.*
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Qualifier

//Обязательно дтолжен быть импорт dagger.Lazy (по стандарту используется котлиновский лейзи)
class App : Application() {
    val appComponent = DaggerAppComponent.create()
}
///////////////////

class MainActivity : AppCompatActivity() {

    lateinit var networkUtilsLazy: Lazy<NetworkUtils>//мы получим этот экземпляр только после обращения  к нему

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkUtilsLazy =
            (application as App).appComponent.getNetworkUtils()//мы получим этот экземпляр только после обращения  к нему
        networkUtilsLazy.get()//этот метод создаст нетворк и вернет его нам(последущие вызовы get вернут тот же экземпляр)
    }

}

@Component(modules = [StorageModule::class])
interface AppComponent {
    fun getNetworkUtils(): Lazy<NetworkUtils>//указываем что нужно возвращать лейзи

    @Named("prod")//чтоб метод возвращал версию прод
    fun getServerApiProd():ServerApi
}

@Module
class StorageModule() {
    @Provides
    fun provideDataHelper(): DatabaseHelper {
        return DatabaseHelper()
    }

    @Provides
    fun provideNetworkUtils(): com.example.daggertest.NetworkUtils =
        com.example.daggertest.NetworkUtils()
}

//NAMED
@Module
class ProviderModule() {//провайдсы возвращают 2 одинаковых класса с разными входными параметами
    @Named("prod")//разделяем их по имени
    @Provides
    fun provideServerApiProd(): ServerApi {
        return ServerApi("prod.server.com")
    }
    @Named("dev")
    @Provides
    fun provideServerApiDev(): ServerApi {
        return ServerApi("dev.server.com")
    }
    //1)(в appComponent)Используем метод get в компоненте, способ получения:
        /*@Named("prod")//чтоб метод возвращал версию прод
        fun getServerApiProd():ServerApi*/

    //2)(в MainActivity) Через Inject
        //@Inject
        //@Named("prod")
        //lateinit var serverApi: ServerApi
}
//Qualifier создаем свои аннотации для нейминга
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Prod//теперь используем их вместо аннотации Named

@Qualifier//Они так же, как и Named, помогут даггеру различать два способа создания объекта ServerApi.
@Retention(AnnotationRetention.RUNTIME)//вызываются они для гет и инжект точно так же
annotation class Dev(val version:String = "")//можно добавлять аргумент ,если у нас несколько версий Dev экземпляра(Dev1,Dev2)(используется точно так же как у Named)



//IntoSet - получить Set
interface EventHandler{}//несколько классов являются евент хендлерами
class Analytics:EventHandler{}//получаем сразу набор этих классов,а не по одному
class Logger:EventHandler{}

@Module
class HandlerModule(){
    @Provides
    @IntoSet//аннотация для того чтоб можно было собрать все в Set()
    fun provideAnalytics():EventHandler = Analytics()//Возвращать нужно EventHandler
    @Provides
    @IntoSet
    fun provideLogger():EventHandler = Logger()
}
    //а получить можно в AppComponent...
        //fun getEventHandlers(): Set<EventHandler>

//@ElementsIntoSet??? типа сета что-то





//@IntoMap - Получаем мапу из элементов
//создаем классы(они в сете созданы)
@Module
class MapHandlerModule(){
    @IntoMap//чтоб можно было собрать этот объект в мапу
    @StringKey("analytics")//какой ключ будет в мапе
    @Provides
    fun provideAnalytics(): EventHandler {
        return Analytics()
    }

    @IntoMap
    @MyKey(Type.LOGGER)//это свой ключ в виде енама для мап
    @ClassKey(DatabaseHelper::class)//ввиде ключа для мапы можно предоставлять и классы
    @IntKey(2)//ключ для мапы может быть не только стрингом
    @Provides
    fun provideLogger(): EventHandler {
        return Logger()
    }
}
    @Component
    interface AppComponent2{
        fun getEventHandlers():Map<String,EventHandler>//все эти коллекции можно получать только через гет методы
    }

    //создаем свой ключ для мапы в виде Енама
    enum class Type {
        ANALYTICS, LOGGER
    }
    @MapKey//указываем что это новый ключ для мапы
    @Retention(AnnotationRetention.RUNTIME)
    annotation class MyKey(val value: Type)//указываем элемент,который будет приниматься в качестве ключа

class DatabaseHelper {}
class NetworkUtils {}
class ServerApi(str: String) {}