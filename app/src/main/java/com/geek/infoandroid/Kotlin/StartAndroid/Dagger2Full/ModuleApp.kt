package com.example.testcode

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.Resources
import dagger.Module
import dagger.Provides
//передаем объекты в компонент(прокидываем контекст)
@Module
class ModuleApp (private val context: Context){
    @Provides
    fun getPreferences():SharedPreferences{
        return context.getSharedPreferences("prefs",MODE_PRIVATE)
    }
    @Provides
    fun getResources():Resources{
        return context.resources
    }
}