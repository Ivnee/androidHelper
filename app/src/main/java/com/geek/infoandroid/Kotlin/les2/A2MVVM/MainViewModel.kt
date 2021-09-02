package com.example.infokotlin.A2MVVM

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel

class MainViewModel(resourceProvider: ResourceProvider) :
    ViewModel() {//может принимать Аппликейшн,потому что он переживет вьюмодель
// ,а контекст (активити и фрагмент) не переживет,лучше чтоб он это принимал через ресурс провайдер

    fun takeContext(context: Context) {}//вьюмодель принимает контект только в методах
}