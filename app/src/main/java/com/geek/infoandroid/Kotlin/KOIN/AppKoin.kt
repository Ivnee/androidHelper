package com.geek.infoandroid.Kotlin.KOIN

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppKoin : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{//создаем коин
            androidContext(this@AppKoin)//предоставили контекст для нашего апп
            modules(listOf(application))
        }
    }
}