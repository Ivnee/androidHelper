package com.example.gitt.cicerone

import com.github.terrakok.cicerone.Router

class Presenter(private val router: Router) {
    fun work(){
        router.exit()//выйти с текущего экрана
    }
}