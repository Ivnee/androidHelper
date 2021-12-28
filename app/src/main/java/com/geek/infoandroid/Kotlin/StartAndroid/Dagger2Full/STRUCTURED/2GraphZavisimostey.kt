package com.example.daggertest

import dagger.Component
import dagger.Module
import dagger.Provides

class MainActivityPresenter(
    private val databaseHelper: DatabaseHelper,
    private val networkUtils: NetworkUtils
) {
}

@Module
class MainModule {
    @Provides
    fun provideMainActivityPresenter(
        databaseHelper: DatabaseHelper,//закидываем зависимости в аргументы функции
        networkUtils: NetworkUtils//т.к. даггер умеет создавать дб и нетворк,он их прокинет сам
    ): MainActivityPresenter = MainActivityPresenter(databaseHelper,networkUtils)//и вставляем их в наш экземпляр
}
@Component(modules = [StorageModule::class])
interface AppComponent2 {
    fun getMainActivityPresenter(): MainActivityPresenter//получаем наш презентр гет методом
}
//получаем в MAIN ACTIVITY презентер
// lateinit var mainActivityPresenter: MainActivityPresenter
// mainActivityPresenter = (application as App).appComponent.getMainActivityPresenter()