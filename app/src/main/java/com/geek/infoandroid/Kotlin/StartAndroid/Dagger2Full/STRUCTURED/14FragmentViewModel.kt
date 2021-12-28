package com.example.daggertest.`14Fragment`

import androidx.fragment.app.Fragment
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.daggertest.App
import com.example.daggertest.MainModule
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

//Первый сценарий
//1)
class LoginUsernameFragment: Fragment() {
    @Inject
    lateinit var loginViewModel: LoginViewModel//после инжекта(ниже)получим нашу вьюмодель

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as LoginActivity).loginComponent.inject(this)//получаем у активити нащ сабкомпонент и инжектимся в него
    }
}
//2)Создаем фабрику для созданию вью моделей
class ViewModelFactory @Inject constructor(//здесь фабрика у нас знае про все вью модели,нарушает SOLID
    val networkUtils: NetworkUtils,//инжектим нужные зависимости
    val databaseHelper: DatabaseHelper
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {//в зависимости от запрашиваемого ,возвращаем вью модель
            ViewModel1::class.java -> ViewModel1(networkUtils)
            ViewModel2::class.java -> ViewModel2(databaseHelper)
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        } as T
    }

}
//3)создаем вью модели
class ViewModel1(val networkUtils: NetworkUtils): ViewModel()
class ViewModel2(val databaseHelper: DatabaseHelper): ViewModel()

//4)создаем сабкомпонент ,который вернет нам фабрики для вью моделей
@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun getViewModelFactory(): ViewModelFactory
}
//5 в активити
class LoginActivity :AppCompatActivity(){
    val mainComponent = (application as App).appComponent.getMainComponent().create(this)//получаем сабкомпонент

    val viewModelFactory = mainComponent.getViewModelFactory()//получаем фабрику

    val model1 = ViewModelProvider(this, viewModelFactory).get(ViewModel1::class.java)//создаем обе вью модели
    val model2 = ViewModelProvider(this, viewModelFactory).get(ViewModel2::class.java)
}
//Способ2
//1)
@MapKey//Создаем ключ,чтоб можно было собрать все в мапы
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)
//2)
@Module//создаем модуль который генерит все вью модели
class ViewModelModule {

    @IntoMap//аннотация говорит что эти объекты можно собирать в мапу
    @ViewModelKey(ViewModel1::class)//ключ мапы
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
//3)хз лучше еще раз почитать и попробовать применить,не очень понял способ2
class ViewModelFactory2 @Inject constructor(
    private val viewModelProviders:Map<Class<out ViewModel>, Provider<ViewModel>>//Provider(чтоб каждый раз нам создавалась новая вью модель)
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModelProviders[modelClass]?.get() as T
            ?: throw IllegalArgumentException("Unknown ViewModel class")
    }

}

///////////
class DatabaseHelper {}
class NetworkUtils {}