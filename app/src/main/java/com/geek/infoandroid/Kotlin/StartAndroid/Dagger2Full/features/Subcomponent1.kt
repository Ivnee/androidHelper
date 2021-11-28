package com.example.testcode.features

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testcode.*
import com.example.testcode.operators.Inject2
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import dagger.Module
import dagger.Provides
import javax.inject.Inject

//Сабкомпонент можно сказать наследник компонента(т.е. в сабкомпонент мы можем попросить любой объект из модулей компонента)
class Subcomponent {
    @Component(modules = [ModuleForSubcomponents::class,StorageModule::class, NetworkModule::class])
    interface AppComponent {
        fun getDatabaseHelper(): DatabaseHelper
        fun getNetworkUtils(): NetworkUtils

        //указали что можно передать туда модуль(теперь при создании необходимо будет его передавать)
        fun getMainComponent(storageModule: StorageModule): MainComponent//чтоб наш компонент умел создавать сабкомпонент создаем этот метод

        //как получить сабкомпонент с кастомным билдером
        fun getMainComponentBuilder(): MainComponent.Builder//создаст наш сабкомпонент с этим билдером,после поулчения билдера сами создаем мэйн компонент

        //как получить Factory саб компонент
        fun getMainComponentFactory(): MainComponent.Factory//в компоненте мы возвращаем фэктори нашего сабкомпонента и идем в активити

        //как заинжектить мейн билдер у сабкомпонента в активити
        fun injectMainActivity(activity: MainActivity)
    }
}

@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun getMainActivityPresenter(
        databaseHelper: DatabaseHelper,
        networkUtils: NetworkUtils
    ): MainActivityPresenter//зависимости он возьмет так же из модулей основного компонента

    fun getDatabaseHelper(): DatabaseHelper//сам сабкомпонент незнает как создать этот объект,нет модулял.Но сам компонент имеет модуль в котором есть нужный провайдер,к нему и обращается сабкомпонент
    //тоесть сабкомпонент имеет доступ ко всем модулям из компонента

    //Создаем билдер для сабкомпонента
    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: Activity): Builder
        fun build(): MainComponent//метод создания
    }

    //Factory для сабкомпонента(аналог билдера,но короче)
    @Subcomponent.Factory
    interface Factory {
        //в родительском компоненте описываем метод,который вернет эту фабрику
        fun create(@BindsInstance activity: Activity): MainComponent//создается фэктори через криейт ,который просит активити на вход
    }

    fun getClassWithActivity(activity: Activity): Inject2.Cat//поmом в этом классе юзаем нашу активити
}

@Module(subcomponents =[MainComponent::class] )//чтобы заработал сабкомпонент ,обязательно,в любом модуле компонента прописать наш сабкомпонент
class ModuleForSubcomponents{}
@Module
class MainModule() {
    @Provides//тут показываем как прокидывать активити в презентер из сабкомпонента ,помимо прочего
    fun provideMainActivityPresenter(
        activity: Activity,
        databaseHelper: DatabaseHelper,
        networkUtils: NetworkUtils
    ): MainActivityPresenter {
        return MainActivityPresenter(databaseHelper, networkUtils)
    }
}


class MainActivity : AppCompatActivity() {
    //как получить презентер в активити или другие классы сабкомпонента
    val appComponent = (application as App).appComponent
    val mainComponent = appComponent.getMainComponent()//создание сабкомпонента
    val mainComponentWithArgument =
        appComponent.getMainComponent(StorageModule())//когда передаем модули в ручную //если нужен модуль из кода активити например
    val mainComponentWithBuilder = appComponent.getMainComponentBuilder.activity(this)
        .build()//создаем сабкомпонент через кастомный билдер,в который передаем нашу активити
    val mainComponentWithFactiry =
        appComponent.getMainComponentFactory().create(this)//создали сабкомпонент через фабрику
    val presenter = mainComponent.getMainActivityPresenter()

    //получаем инжект сабкомпонента
    @Inject
    lateinit var mainComponentBuilder: MainComponent.Builder//получаем билдер нашего сабкомпонента
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        appComponent.injectMainActivity(this)//инжектим нашу активити
        val mainSubcomponent = mainComponentBuilder.activity(this).build()//создаем наш сабкомпонент

    }


}