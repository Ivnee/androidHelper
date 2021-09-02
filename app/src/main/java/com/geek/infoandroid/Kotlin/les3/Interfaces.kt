package com.geek.infoandroid.Kotlin.les3

import android.util.Log

/*
sealed interface OnClick {//sealed интерфейс в рамках одного модуля можем наследоваться от данных интерфейсов и создают строгую типизацию и оператор when может проверять по типам таких интефрейсов

    fun onClick()
}
*/

interface OnResult<T> {

    val point: Point

    var mutablePoint: Point

    fun onSuccess(value: T)

    fun onError(error: Throwable) = Log.e("TAG", "Some Error Happened", error)
}

/*fun interface Predicate<T> {

    fun apply(value: T) : Boolean
}*/

class InterfaceClass : OnResult<PhoneNumber> {//класс который реализует интерфейс ,интерфейс обязан переопределить
    override fun onSuccess(value: PhoneNumber) = Unit

    override val point: Point
        get() = TODO("Not yet implemented")
    override var mutablePoint: Point
        get() = TODO("Not yet implemented")
        set(value) {}
}