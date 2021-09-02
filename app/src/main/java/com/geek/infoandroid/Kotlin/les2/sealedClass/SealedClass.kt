package com.geek.infoandroid.Kotlin.les2.sealedClass

sealed class SealedClass {//класс объединение типа енума

}

class Sunny(private val power: Int) : SealedClass()//варианты этого класса(енум элементы типа)
class Cloudy(private val level: Int) : SealedClass()
class Fog(private val distance: Int) : SealedClass()

fun main(sealedClass: SealedClass) {
    when (sealedClass) {//если сеалед класс это
        is Sunny -> {//санни ,то делаем его код
        }
        is Cloudy -> {//если клоуди то его
        }
        is Fog -> {
        }
    }
}