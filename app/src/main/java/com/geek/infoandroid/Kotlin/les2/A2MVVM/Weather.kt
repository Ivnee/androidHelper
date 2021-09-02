package com.example.infokotlin.A2MVVM
@JvmInline
value class Temperature(val value: Double){//создали поле класс температуры и теперь у этого поля можем создавать свои методы
    fun isBelowZero():Boolean{
        return value < 0
    }
}

data class Weather(val temperature: Temperature, val id: String) {
}