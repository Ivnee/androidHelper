package com.example.testcode.features

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

class GetContext :Application(){
    //есть моодуль которому необходимо прокинуть контекст в зависимости
    @Module
    class AppModule(private val context: Context) {
        @Provides
        fun getPreferences(): SharedPreferences {
            return context.getSharedPreferences("prefs", MODE_PRIVATE)
        }
        //контекст у нас вне модуля на данном этапе и мы не можем его прокинуть в зависимости нашим классам ,для этого нужно ..
        @Provides
        fun provideContext():Context{
            return context
        }
    }//добавляем этот модуль в наш компонент

    //в App создаем наш DaggerAppComponent через билдер
    val appComponent = DaggerAppComponent
        .builder()
        .appModule(AppModule(this))//передаем сюда наш аппликейшн
        .build()

}