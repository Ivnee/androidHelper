package com.example.testcode.hilt

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.example.testcode.operators.ActivityScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

//какая иерархия компонентов в хилте (AppComponent > ActivityComponent > FragmentComponent)соответственно
//фрагмент компонент будет наследовать все что умеют апп компонент и активити компонент ...
//АПП
@HiltAndroidApp//эта аннотация создаст для нас апп обертку хилт и АппКомпонент для него
class App2:Application(){
    @Inject
    lateinit var databaseHelper: DatabaseHelper//сразу получаем готовый объект в класс апп,который можно запросить
    @Inject lateinit var networkUtils: NetworkUtils//предоставляем нетворкутилс из модуля ,который описали внизу
}
//АКТИВИТИ
@AndroidEntryPoint//эта анно добавить инжект в сабкомпонент хилта(под копотом) инжект нашей активити и класс обертку для активити,в котором будет инжектится наш активити
class OrderActivity2:AppCompatActivity(){
    @Inject//и мы сразу сможем получать нужные нам объекты(создаст в методе он креейт)
    lateinit var orderRepository: OrderRepository
}

@AndroidEntryPoint//этой аннотацией можно помечать  Activity, фрагмент, сервис, View и Broadcast Receiver.
class UserActivity2:AppCompatActivity(){
    @Inject
    lateinit var repository: UserRepository
}

@AndroidEntryPoint
class OrderFragment2:Fragment(){
    @Inject
    lateinit var repository: OrderRepository//использовать можно начиная с он креейте и даже с он аттач
}
//Если создаем в модуле наши объекты
@Module
//@InstallIn(SingletonComponent::class)//в хилте наоборот,указываем в модуле компонент(синглтон значит на уровне апп)
@InstallIn(ActivityComponent::class)//значит компонент имеет жизненный цикл активити,которая инжектилась(теперь на уровне апп мы не сможем создать нетворк утилс,на ур фрагмента и активити сможем)
//@InstallIn(FragmentComponent::class)
class NetworkModule {
    //ЕСЛИ этот модуль использует SingletonComponent, мы модем запрашивать здесь аппликейшн по умолчанию
    //если Активити компонент,то активити ...ниже список того что можно запросить по умолчанию
    @Provides
    fun provideNetworkUtils(application: Application): NetworkUtils {
        return NetworkUtils()
    }
}
@Singleton//синглтон на уровне апп
@ActivityScoped//указываем какаой синглтон создавать,синглтон на уровне активити
@FragmentScoped//синглтон на уповне врагмента
class NetworkUtils @Inject constructor(){}
/*
SingletonComponent	- Application2
ActivityRetainedComponent	- Application
ViewModelComponent	- SavedStateHandle
ActivityComponent	- Application, Activity
FragmentComponent	- Application, Activity, Fragment
ViewComponent	- Application, Activity, View
ViewWithFragmentComponent	- Application, Activity, Fragment, View
ServiceComponent	- Application, Service*/
