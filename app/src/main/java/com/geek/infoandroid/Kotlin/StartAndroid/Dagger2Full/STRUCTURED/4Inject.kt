package com.example.daggertest.`4inject`

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.daggertest.App
import com.example.daggertest.R
import javax.inject.Inject
import javax.inject.Qualifier


class MainActivityPresenter @Inject constructor(//аннотация инжект создает этот класс(теперь нам не нужны провайд методы в Module)
    private val databaseHelper: DatabaseHelper,//если даггер знает как их создават,подставить автоматически,и не обязательно в модуле создавать, можно через @Inject constructor()
    @Prod private val networkUtils: NetworkUtils//если нам нужна конкретная версия объекта,просто указываем квалифаер
) {
    /*- может быть получен от компонента get-методом
    - может быть заинджектен компонентом
    - может быть использован как аргумент в Provides методе создания другого объекта*/
}




class DatabaseHelper @Inject constructor() {//метод Inject вызывается только в случае,если объект был создан через Inject
    @Inject//методы помечанные инжектом вызываются сразу после создания объекта даггером
    fun postInit(){}
}

//Inject в MAIN ACTIVITY...
    //(application as App).appComponent.injectMainActivity(this)//инжектим нашу активити
    @Inject//создаем в активити метод с аннотацией инжект,после инжекта она будет вызвана автоматичечски
    fun postInit(networkUtils: NetworkUtils) {
        // ...
    }

/////////////////////
class NetworkUtils @Inject constructor(){}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Prod