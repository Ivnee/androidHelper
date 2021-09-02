package com.geek.infoandroid.Kotlin.les3

class NullableTest {

    var variable: String? = "4"
    fun mainFunc() {
        variable = null//чтоб равна была нул ,нужно указать тип наллабл(?)
        variable!!.compareTo("2")//если variable нулл то упадет(через вопрос просто продолжается выполнение кода,приложение не падает)
        variable?.compareTo("132") ?: throw NullPointerException("значение нулл")
        var any: Any = "JavaObject"//это это как джава обж
        val castToStr = any as String//кастанули к стрингу,если не получится,вернет нулл и приложение упадет
        val castToStrNull: String? = any as? String//если кастанется,вернет стринг,если нет,вернет нулл

        //СПИСКИ
        val list1= listOf<String>()//лист стрингов
        val mutableList:MutableList<String> = mutableListOf<String>()//листо который можно мменять
        val array:Array<String> = arrayOf("1","2","3")//аррай лист стрингов
        array.forEach { print(it) }//распечатать все элементы списка

        val itemsList = listOf("one","two","three")
        for (i in itemsList.indices){ print(itemsList[i])}//перечислить все элементы
        //мэп копирует коллекцию
        val lengths:List<Int> = itemsList.map { it.length }.map { it/2 }//мап преобразует список,мы получим список из чисел(длина слов), и потом элементы полученного списка поделим на 2

        //создаем Sequence(дргая последовательность выполнениее,ниже подробнее)
        val lengthsSeq:Sequence<Int> = itemsList.asSequence().map { it.length }.map { it/2 }//в этой цепочке мы у первого элемента сначала найдем длину,потом поделим на 2,затем перейдем к следующему элементу(раздница в последовательности выполнения)
        lengthsSeq.toList()//преобразоваьб сиквенс в лист
        val sequenceBuilder = sequence {
            yield("1")//добавляем элементы в него через оператор yield
            yield("2")
            yield("3")
        }

        val count:Int = itemsList.count{it.length>5}//вернет количество строк у которых длина больше 5
        val count2:Boolean = itemsList.any{it.length==3}//проверяет ,удовлетропяет хотябы 1 элемент это условие (ВОЗВРАЩАЕТ БУЛИН)
        val count3:Boolean = itemsList.all { it.length <5 }//удовлетворяет ли это условие все элементы списка(ВОЗВРАЩАЕТ БУЛИН)
        itemsList.reversed()//перевернуть список и много всяких действий как у аррай листа
        itemsList.asReversed()//вернет перевернутую копию этого списка
        itemsList.first()
        itemsList.last()
        itemsList.getOrElse(1,{228})//с дефолтным значением

        val array2 = arrayOf("one","two","three")
        val range:IntRange= array2.indices//вернет список индексов от 0.. до последнего
        //points
        var points = listOf(
            Point(1,2),
            Point(3,3),
            Point(4,4),
            Point(5,5),
            Point(6,6)
            )
        val a:Int = points.count{point -> point.x >5 && point.y<5 }//сколько будет элементов ,которые соблюдают это условие
        val b=points.map{it.x+it.y}.filter { it>10 }//вернет лис интов ,которые больше 10(сумма х и у которая больше 10)
        //points.minOrNull()//вернет мин значение
        points.maxByOrNull { it.y+it.y}//максимальное число вернет
    }
}
data class Point(val x:Int,val y:Int){}