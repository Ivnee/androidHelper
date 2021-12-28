package com.example.daggertest.`9ComponentDependencies`

import android.app.Application
import com.example.daggertest.MainModule
import dagger.Component
import dagger.Module
import dagger.Provides

// Компоненты создаются отдельно друг от друга. Они общаются на уровне интерфейсов и не имеют доступа к модулям. И это большая разница.
@Component(modules = [StorageModule::class, NetworkModule::class])
interface AppComponent {
    fun getDatabaseHelper(): DatabaseHelper//явно прописываем методы гет,чтоб MainComponent смог их получать отсюда,для своих модулей
    fun getNetworkUtils(): NetworkUtils
}
//сейчас мы опишем MainComponent, как обычный компонент (не сабкомпонент):
@Component(modules = [MainModule::class], dependencies = [AppComponent::class])//депенденсиес указывает что мэйн компонент
interface MainComponent {// имеет доступ ко всем объектам Апп компонента
    fun getMainActivityPresenter(): MainActivityPresenter
    //. MainComponent может попросить только те объекты, которые явно прописаны get методами в интерфейсе компонента AppComponent
}
class App:Application(){//В итоге создание компонента MainComponent выглядит так:
    //val mainComponent = DaggerMainComponent.builder()
      //  .appComponent(appComponent)Просто при создании нужно будет передать экземпляр апп компонента в билдер
      //  .build()
}


@Module//модуль который используется в MainComponent
class MainModule {//для создания в нем презентора,необхоимо взять databaseHelper networkUtils
    //Это означает, что в AppComponent нам необходимо явно прописать get методы для этих объектов
    @Provides
    fun provideMainActivityPresenter(databaseHelper: DatabaseHelper, networkUtils: NetworkUtils): MainActivityPresenter {
        return MainActivityPresenter(databaseHelper, networkUtils)
    }
}




@Module
class StorageModule {}
@Module
class NetworkModule {}

class DatabaseHelper {}
class NetworkUtils {}
class MainActivityPresenter(databaseHelper: DatabaseHelper,networkUtils: NetworkUtils)