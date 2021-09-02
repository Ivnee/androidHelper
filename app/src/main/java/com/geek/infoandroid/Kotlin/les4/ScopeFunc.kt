package com.geek.infoandroid.Kotlin.les4

import android.content.Intent
import androidx.fragment.app.Fragment
import com.geek.infoandroid.MainActivity
import com.geek.infoandroid.R

class ScopeFunc : Fragment(R.layout.fragment1) {
    private val data = mutableListOf("1", "2", "3")
    fun addData() {
        data.apply {
            //применяется если хотим изменить состояние объекта
            //принимает this(обьект у которого вызываем апплай,тоесть data) возвращаем this,сама дата с изменениями,которые мы сделали
            add("s")
            clear()
        }
        data.let {
            //принимает как it (как параметр),возвращает результат лямбда выражения(если нечего не возвращать,будет тип возвращаемого значения Unit(void))
            it.add("asd")
            it[1]
            return@let it[0]//можно вообще без ретерна,по умолчанию Unit
        }
        with(data) {
            //применяется для изменения состояния объекта + вернуть результат
            //передает сам объект и мы вызываем у него все нужные нам методы
            //возвращает результат лямбды
            removeAt(2)
            set(0, "0")
            addAll(listOf("1", "2", "3"))
            return@with true
        }//можем сохранить интент который вернется из also(также)
        val intent: Intent = Intent(
            requireActivity(),
            MainActivity::class.java
        ).also { requireActivity().startActivity(it) }//передает it и возвращает this(тоесть наш интент)

        data.run {
            //принимает на вход this
            add("10")
            add("11")
            return@run true//вернули результат лямбды
        }
    }

    ////////////////////////////////
    fun getMovies(
        callback: ((result: RepositoryResult<List<Int>>) -> Unit)? = null,//сделали тип функции nullable и теперь можн оприсвоить значение этой функции Null(здесь null по дефолту,можно вообще эту функцию не трогать)
        callback2: (result: RepositoryResult<List<Int>>) -> Unit //
    ) {
        callback?.invoke(RepositoryResult())//если налабл вызывается только через ?.invoke
        callback2.invoke(RepositoryResult())//обычный вызов
    }

}

class RepositoryResult<T> {

}
