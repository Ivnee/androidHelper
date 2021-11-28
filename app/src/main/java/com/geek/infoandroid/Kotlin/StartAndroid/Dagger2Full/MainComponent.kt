package com.example.testcode

import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun getMainActivityPresenter():MainActivityPresenter
}