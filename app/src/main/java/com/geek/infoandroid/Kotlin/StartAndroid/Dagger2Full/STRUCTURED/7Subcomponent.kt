package com.example.daggertest.`7Subcomponent`

import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Component(modules = [StorageModule::class, NetworkModule::class])
interface AppComponent {
    fun getDatabaseHelper(): DatabaseHelper
    fun getNetworkUtils(): NetworkUtils

    fun getSubcomponent():MainComponent//компонент должен уметь создавать наш сабкомпонент
}
//создаем сабкомпонент
@Subcomponent(modules = [MainModule::class])//так же, как у компонента создаем гет метод и указываем модули
interface MainComponent{//сабкомпонет имеет все тоже самое что и компонент,просто расширяет это своими етодами
    fun getMainActivityPresenter():MainActivityPresenter
}
////////////////////////////////////////--
@Module
class MainModule {
    @Provides
    fun providesMainActivityPresenter(
        databaseHelper: DatabaseHelper,
        networkModule: NetworkModule
    ): MainActivityPresenter = MainActivityPresenter(databaseHelper, networkModule)
}

@Module
class StorageModule {}

@Module
class NetworkModule {}

class MainActivityPresenter(databaseHelper: DatabaseHelper, networkModule: NetworkModule) {}
class DatabaseHelper {}
class NetworkUtils {}