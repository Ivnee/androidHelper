package com.example.testcoroutine

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class `23Scope` {
    //ViewModelScope
    class model :
        ViewModel() { //SupervisorJob(не отменит все корутины,если одна верершится)+Dispatchers.Main.Immediate(он не перенаправляет в диспатчер а просто продолжает делать код в мэйн)
        //отличие - main (before(),after(),launch()) main,immediate(before(),launch(),after)
        fun work() {
            viewModelScope.launch { }
        }//скоуп для вью модели,завершится в методе onCleared
    }

    //lifecycleScope
    class Fragment1 : Fragment() {
        //SupervisorJob() + Dispatchers.Main.immediate
        fun work() {
            lifecycleScope.launch {}//скоуп подписанный на жизненный цикл активити или фрагмента(при повороте будет отменен и создан новый)
            lifecycleScope.launchWhenStarted { }//запустит ,когда вызовется метод onStart(есть для всех циклов)
        }

        //MainScope
        val scope = MainScope()//SupervisorJob() + Dispatchers.Main
        fun work3() {
            scope.launch { }//скоуп мейн потока,отменять нужно самим
        }

        //CoroutineScope - если нужно создать свой скоуп
        val scope1 =
            CoroutineScope(Job() + Dispatchers.Main + CoroutineExceptionHandler { context, throable -> })//тоже отменять нужно самому

        //LiveData + Coroutine
        val liveData =
            liveData<String> {//билдер лайфдаты создает корутину и возвращает livedata(корутина стартует когда на лайфдату подписываются)
                //при повороте экрана она не будет убита,но если подписчика не будет больше 5 секунд то она умрет,и при подписке создастся по новой
                emit("work")
                latestValue//вернет последнее значение которое отправил emit
            }//отправляем данные через emit
    }
}