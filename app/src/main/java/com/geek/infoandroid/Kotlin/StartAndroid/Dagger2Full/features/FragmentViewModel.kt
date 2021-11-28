package com.example.testcode.features

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testcode.App
import com.example.testcode.DatabaseHelper
import com.example.testcode.NetworkUtils
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

//получаем компонент во фрагменте
class LoginUsernameFragment: Fragment() {
    //@Inject
    //lateinit var loginViewModel: LoginViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
////////////////////////////////////////////////////////
        //(activity as LoginActivity).loginComponent.inject(this)
//////////////////////////////////////////////////////////////////////////
    }

}
//ViewModel
class ViewModel1(val networkUtils: NetworkUtils): ViewModel()
class ViewModel2(val databaseHelper: DatabaseHelper): ViewModel()

//фабрика для создания вью модели
class ViewModelFactory @Inject constructor(
    val networkUtils: NetworkUtils,
    val databaseHelper: DatabaseHelper
): ViewModelProvider.Factory {
    //ЗДЕСЬ ФАБРИКА ЗНАЕТ О ВЬЮ МОДЕЛЯХ И ЧТО ИМ НУЖНО ПЕРЕДАВАТЬ В АРГУМЕНТЫ
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            ViewModel1::class.java -> ViewModel1(networkUtils)
            ViewModel2::class.java -> ViewModel2(databaseHelper)
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        } as T
    }


}
@dagger.Subcomponent(modules = [MainModule::class])
interface MainComponent2 {//создаем компонент в котором будем получть фэктори
    fun getViewModelFactory(): ViewModelFactory
}




// В Activity вызываем этот get метод, получаем фабрику и используем ее для создания моделей:
class LoginActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        val mainComponent = (application as App).appComponent.getMainComponent().create(this)

        val viewModelFactory = mainComponent.getViewModelFactory()

        val model1 = ViewModelProvider(this, viewModelFactory).get(ViewModel1::class.java)
        val model2 = ViewModelProvider(this, viewModelFactory).get(ViewModel2::class.java)
    }
}

//НУЖНО СДЕЛАТЬ ЧТОБ ФАБРИКА НИЧЕЧГО НЕ ЗНАЛА О ВЬЮ МОДЕЛЯХ
//1)создаем ключ для нашей аннотации
@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)
//2)СОЗДАЕМ МОДУЛЬ С ВЬЮ МОДЕЛЯМИ, который будет кидать их в мапу и ключем будет название этой вью модели
@Module
class ViewModelModule {
    @IntoMap
    @ViewModelKey(ViewModel1::class)
    @Provides
    fun provideViewModel1(networkUtils: NetworkUtils): ViewModel {
        return ViewModel1(networkUtils)
    }

    @IntoMap
    @ViewModelKey(ViewModel2::class)
    @Provides
    fun provideViewModel2(databaseHelper: DatabaseHelper): ViewModel {
        return ViewModel2(databaseHelper)
    }
}
class ViewModelFactory2 @Inject constructor(
    private val viewModelProviders: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>//создаем провайдер для вью модели
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModelProviders[modelClass]?.get() as T//получаем вью модель из сиска мап по имени вью модели
            ?: throw IllegalArgumentException("Unknown ViewModel class")
    }

}