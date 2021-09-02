package com.example.infokotlin.A2MVVM

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.*
import com.example.infokotlin.R

class MVVMActivity : AppCompatActivity(R.layout.activity_mvvmactivity) {
    val someClass = SomeClass()
    private lateinit var viewModel: MainViewModel

    //можно объявить вью модель через экстеншен
    private val viewModel2:MainViewModel by viewModels{
        MainViewModelFactory(ResourceProvider(Application()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(someClass)//теперь этот класс следит за жизненным циклом активити
        ///объявляем вью модель и фэктори

        val factory = MainViewModelFactory((ResourceProvider(Application())))
        viewModel = ViewModelProvider(this,factory).get(MainViewModel::class.java)

    }
}

class SomeClass:LifecycleObserver{

    @OnLifecycleEvent(Lifecycle.Event.ON_START)//вызывается в он старте
    fun aquireSomeResource(){}
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)//вызывается в он стопе
    fun releaseSomeResource(){}
}
class MainViewModelFactory(val resourceProvider: ResourceProvider): ViewModelProvider.Factory {//фэктори для
// пропихивания зависимостей в вью модель
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(resourceProvider) as T

    //как запихнуть несколько вью моделей в 1 фэктори
    /*if(modelClass.isAssignableFrom(WeatherDetailsViewModel::class.java)){
        return WeatherDetailsViewModel(resourceProvider, weatherRepository) as T
    }else{
            throw IllegalStateException("")
        }*/
    }
}