package com.geek.infoandroid.Kotlin.les3.dagger.subcomponent

import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides

@Module
class RouterModule {//если у модуля нет сторонних зависимостей он собирается автоматически(имеется ввиду в конструктор класса ничего не кладут)
    @Provides
    fun providesRouter(fm:FragmentManager):MainRouter = MainRouter(fm)
}