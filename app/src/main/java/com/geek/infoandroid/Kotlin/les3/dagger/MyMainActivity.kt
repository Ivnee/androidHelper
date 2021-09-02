package com.geek.infoandroid.Kotlin.les3.dagger

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.geek.infoandroid.Kotlin.les3.dagger.subcomponent.MainActivityModule
import com.geek.infoandroid.Kotlin.les3.dagger.subcomponent.MainRouter
import com.geek.infoandroid.Kotlin.les3.dagger.subcomponent.MainSubcomponent
import javax.inject.Inject

class MyMainActivity: AppCompatActivity() {
    @Inject
    lateinit var router:MainRouter

    var mainSubcomponent :MainSubcomponent? = null
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        //получаем из основного компонента
        (application as? App)?.appComponent?.inject(this)
        //получаем из сабкомпонента
        mainSubcomponent = (application as App).appComponent.mainSubcomponent().create(//создаем наш сабкомпонент
            MainActivityModule(this)//и кидаем в него модуль с нашей активити в зависимости
        )
        mainSubcomponent.inject(this)//подписываем нашу активити в сабкомпонент
    }
}