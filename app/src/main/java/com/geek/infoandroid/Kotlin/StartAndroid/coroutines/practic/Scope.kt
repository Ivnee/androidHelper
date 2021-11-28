package com.geek.infoandroid.Kotlin.StartAndroid.coroutines.practic

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

class Scope: Fragment() {
    fun scope(){
        //1)
        lifecycleScope.launch {//мы создали скоуп который сам уничтожается в соответствии с жизненным циклом этого фрагмента(в он дестрой)
            while (true) {
                delay(1000)
                println("work")
            }
        }
        //2)viewModelScope.launch{} скоуп для вью модели ,сам убивается в методе onCleared()
        //3)
        MainScope().launch {  }//этот скоп никчему не привязан (из жизн циколов) поэтому его нужно самому создавать и самому убивать в onDestroy()
        //4)создаем кастомный скоуп где указываем джоб ,диспатчер(поток), и хэндлер(обработчик ошибок)
        val scope = CoroutineScope(Job()+Dispatchers.Main+ CoroutineExceptionHandler{context,exc-> print(exc.stackTrace)})//
    }



}