package com.example.gitt.cicerone

import androidx.appcompat.app.AppCompatActivity
import com.example.gitt.R
import com.github.terrakok.cicerone.androidx.AppNavigator

class Activity :AppCompatActivity(){
    private val presenter = Presenter(App.router)
    private val navigator = AppNavigator(this, R.id.container)//принимает активити и контейнер,в котором меняются фрагменты
    private val navigatorHolder = App.navigatorHolder//устанавливает навигатор ,которы будет управлять нашими экранами
    override fun onResumeFragments() {//когда появится фрагмент,выщывается этот етод
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)//устанавливаем навигатор,который управляет экранами
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()//удаляем напигатор
        super.onPause()
    }
}