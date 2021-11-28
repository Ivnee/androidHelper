package com.example.gitt.moxy

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainView: MvpView {//указываем что это интерфейс вью для мокси
    @AddToEndSingle//добавит метод в очередь,если такой метод уже в очереди то пишедший заменит его
    fun displayUser(user:String)
}