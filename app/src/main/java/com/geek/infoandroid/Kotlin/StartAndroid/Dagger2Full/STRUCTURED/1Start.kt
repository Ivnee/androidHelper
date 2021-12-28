package com.example.daggertest

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject

//если даггер компонент не создается ( Build -> Make Project (CTRL+F9). )
//если выдает ошибки,когда все норм
//1) Build -> Clean project
//2) File -> Invalidate cache and restart -> Invalidate and restart
//3)поменять 1 буква в классе,который не генерится,и вернуть обратно
//////Если этот код у вас крэшит с NullPointerException, убедитесь, что добавили App класс в манифест.
//МОДУЛЬ
@Module
class StorageModule() {
    @Provides
    fun provideDataHelper(): DatabaseHelper {
        return DatabaseHelper()
    }
    @Provides
    fun provideNetworkUtils():NetworkUtils= NetworkUtils()
}


//КОМПОНЕНТ
@Component(modules = [StorageModule::class,MainModule::class])//подключили модуль,который создаст нам дб хелпер
interface AppComponent {
    fun getDataBaseHelper():DatabaseHelper//метод который вернет дб(созданную в модуле)
    fun getNetworkUtils():NetworkUtils
    fun injectMainActivity2(main:MainActivity2)//прокинет в активити все зависимости(заполнит поля помеченные @Inject)
    }


//АПП В КОТОРОМ СОЗДАЕМ КОМПОНЕНТ(ДОБАВИТЬ В МАНИФЕСТ)
class App : Application() {
    //вариант 1
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
    //вариант2 (короче)
    val appComponent2 = DaggerAppComponent.create()
}

//ACTIVITY
class MainActivity2 : AppCompatActivity() {
    @Inject
    lateinit var injectDb:DatabaseHelper//когда активити заинжектится мы получим этот класс

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val appComponent = (application as App).appComponent//получили компонент

        val db = appComponent.getDataBaseHelper()//получаем нужный класс
        appComponent.injectMainActivity2(this)//заинжектились и получаем все классы ,помеченные Inject
    }
}





///класс которые прокидываем

class DatabaseHelper {}
class NetworkUtils {}