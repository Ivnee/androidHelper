package com.example.testingdagger2

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geek.infoandroid.R
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject//после того как мы в компоненте указали методом куда предоставляем зависимости,зависимости можно получать просто аннотацией @Inject
    lateinit var auto:Auto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as App).appComponent.inject(this)//после того как заинжектили нашу активити получим все нужные зависимости
//получилось гораздо меньше писанины
        auto.startDep()
        auto.depend1

        (application as App).appComponent.getAuto().startDep()//вызываем класс ауто сразу с зависимостями и вызываем у него метод startDep()
        (application as App).appComponent.getAuto().depend1.start()//получили зависимость Auto у которой вызвали ее зависимость Depend1
    }
}