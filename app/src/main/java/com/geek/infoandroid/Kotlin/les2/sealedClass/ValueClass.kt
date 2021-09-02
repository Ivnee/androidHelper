package com.geek.infoandroid.Kotlin.les2.sealedClass

@JvmInline
value class ValueClass(val value:String){//класс- поле ,которому можно создать свои методы,типа у полей int.toString()
    fun isFive() = value == "5"
}