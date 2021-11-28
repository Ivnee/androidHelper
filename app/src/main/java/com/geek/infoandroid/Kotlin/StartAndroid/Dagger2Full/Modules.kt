package com.example.testcode

import dagger.Module
import dagger.Provides

@Module//указываем что это модуль
class StorageModule {
    @Provides//указываем что это поставщик объекта
    fun provideDatabaseHelper(repository: Repository): DatabaseHelper {
        return DatabaseHelper(repository)
    }
    @Provides
    fun provideRepository():Repository{
        return Repository()
    }

}

@Module
class NetworkModule {
    @Provides
    fun provideNetworkUtils(connectionManager: ConnectionManager): NetworkUtils {
        return NetworkUtils(connectionManager)
    }
    @Provides
    fun provideConnectionManager():ConnectionManager{
        return ConnectionManager()
    }
}

@Module
class MainModule {
    @Provides
    fun provideMainActivityPresenter(//когда объекту самому нужны зависимости для создания
        databaseHelper: DatabaseHelper,//при создании презентера, даггер обратится к этому методу,создаст его
        networkUtils: NetworkUtils//затем увидит необходимые зависимости и ,если они прописаны, создаст их автоматически
    ): MainActivityPresenter {
        return MainActivityPresenter(databaseHelper, networkUtils)
    }
}

