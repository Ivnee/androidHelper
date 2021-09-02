 package com.geek.infoandroid.Kotlin.les3.Generics

import java.io.Serializable


class GenericClass<T>(val value:T)where T:Serializable,T:Comparable<String>{//создали дженерик класс с таким типом Т ,который обязан реализовывать интерфейс серриалайзабл и компарабл(в противном случае не будет принмать)
//class GenericClass<T:Serializable>(val value:T)// или так можно записать,если наш Т должен реализовать 1 интерфейс

    fun <T>genericFunc(arg:T){//дженерик функция,тоже может принимать Т с определенным интерфейсом
        val genericClass = GenericClass<String>("10")//создали экземпляр дженерик класса
    }
}


//из лекции про in (extends) и out(super)
class Producer<out T> {//как extends в джава для дженерика(out только возвращает инфу fun не принимают аргументы)
    fun produce() : T? {
        return null
    }
}

class Consumer<in T> {//как super в джава для дженерика(in только принимает инфу в виде аргументов в fun)
    fun consume(arg: T)  {
    }
}

open class AA
open class BB : AA()
open class CC : BB()

fun mainProducer() {

    val producer = Producer<CC>()

    val obj: Producer<BB> = producer
}

fun mainConsumer() {

    val consumer = Consumer<AA>()

    val obj: Consumer<BB> = consumer
}