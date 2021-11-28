package com.example.testcode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.Lazy
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject//помечаем,если делаем объекты чеерез инжект
    lateinit var databaseHelper: DatabaseHelper

    @Inject
    lateinit var networkUtils: NetworkUtils
    lateinit var networkUtilsLazy: Lazy<NetworkUtils>//создаст этот объект,только если мы к нему обратимся

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//получаем объекты из методов
        val appComponent = (application as App).appComponent
        //получаем из сабкомпонента
        val subcomponent = appComponent.getMainComponent()
        val presenter = subcomponent.getMainActivityPresenter()

        databaseHelper = appComponent.getDatabaseHelper()//получаем готовые объекты из компонента
        networkUtils = appComponent.getNetworkUtils()

        networkUtilsLazy = appComponent.getNetworkUtilsLazy()//получаем лейзи объект(создается после обращеня к нему)
        networkUtils = networkUtilsLazy.get()//обращаемся к нему
// получаем из инжекта
        val appComponent2 =
            (application as App).appComponent.injectMainActivity(this)//заинжектили нашу активити

    }
}