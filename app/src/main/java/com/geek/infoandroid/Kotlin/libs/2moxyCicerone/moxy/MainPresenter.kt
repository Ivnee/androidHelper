package com.example.gitt.moxy

import moxy.MvpPresenter

class MainPresenter : MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {//вызывается при присоединении вью к презентору
        super.onFirstViewAttach()
        viewState.displayUser("user")//вызываем метод у вьюхи
    }
}