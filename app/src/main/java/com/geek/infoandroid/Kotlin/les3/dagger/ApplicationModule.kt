package com.geek.infoandroid.Kotlin.les3.dagger

import android.app.Application
import dagger.Module
import dagger.Provides

@Module//здесь хранятся методы которые позволяют разрешить те или иные зависимости(если попросить аппликейшн,деггер сам найдет где его взять)
class ApplicationModule(private val app:Application) {//передаем наш Апп
    @Provides
    fun providesApplication(): Application = app//функция возвращает тип Application   (наш app)
}