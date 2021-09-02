package com.geek.infoandroid.Kotlin.les3.dagger.subcomponent

import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
//модуль который используем для получения нашей активити
@Module
class MainActivityModule(private val activity:AppCompatActivity) {//если у модуля сторонние зависимости,то в объявлении фэктори
// в основном компоненте потребуется передать в зависимость MainActivityModule
    @Provides
    fun providesActivity() = activity
    @Provides
    fun providesFragmentManager() = activity.supportFragmentManager
}

/*КАК ПОДПИСЫВАЕМ НАШУ АКТИВИТИ И КАК ПРОПИХИВАЕМ ЕМУ ЗАВИСИМОСТЬ

    var mainSubcomponent :MainSubcomponent? = null


         mainSubcomponent = (application as App).appComponent.mainSubcomponent().create(
                     MainActivityModule(this)
        )
        mainSubcomponent.inject(this)

        КАК ПОДПИСЫВАЕМСЯ В ФРАГМЕНТАХ
        (context as? MainActivity)?.mainSubcomponent?.inject(this)


        КАК ПОДПИСЫВАЕМ В СЕРВИСЕ

       (applicationContext as App).appComponent.inject(this)

* */