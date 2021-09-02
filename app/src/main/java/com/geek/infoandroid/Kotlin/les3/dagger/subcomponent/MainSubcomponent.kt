package com.geek.infoandroid.Kotlin.les3.dagger.subcomponent

import com.example.testingdagger2.MainActivity
import com.geek.infoandroid.Kotlin.les2.MainFragment
import com.geek.infoandroid.Kotlin.les3.dagger.MyMainActivity
import dagger.Subcomponent
//создается чтоб жил пока живет контекст у активити,фрагментов и тд
//как у компонента тоже указываются модули
@Subcomponent(modules =[RouterModule::class,MainActivityModule::class] )//чтобы собрать роутер,для которого требуется фрагмент менеджер нужно MainActivirtModule
interface MainSubcomponent {
    @Subcomponent.Factory
    interface Factory{
        fun create(mainActivityModule: MainActivityModule): MainSubcomponent//передаем мейн активити модуль потому что там требуется пробпосить зависимость в самом классе
    }
    fun inject(main: MyMainActivity)
    fun inject(main:MainFragment)
}

