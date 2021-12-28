package com.geek.infoandroid.Kotlin.les1

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
class newCommands {
    lateinit var someValue: String

    private var privateSet: String = ""
        //запретить изменение этого поля
        private set//из других классов

    fun mainFunc() {
        someValue = "1"
        if (::someValue.isInitialized) {
            someValue = "2"
            println()
        }//проверка свойства на инициализацию
    }

    ////////////////функции с коллбеком
    fun callbackFun(callback: ((arg1: Int, arg2: Int) -> String)? = null) {//значение по умочанию = нулл,можно вызывать
        // метод без этого коллбека
        callback?.invoke(1, 3)//запускается в функции useCallbackFun и возвращает результат нам
    }

    fun funcForArgument(a: Int, b: Int): String =
        "$a + $b"//определили функцию которая принимает 2 инта и возвращает
// результат

    fun useCallbackFun() {
        callbackFun { one, two ->//получаем из метода callbackFun
            val result = one + two
            return@callbackFun result.toString()//передастся результат в функцию callbackFun
        }
        callbackFun(::funcForArgument)//вставляем уже составленныую функцию
    }

    //////////////////
    data class PoJo(val a: Int, val b: Int, val c: Int = 0)//дата класс(автогеттеры и сеттеры)

    fun dataFun() {
        val foo = PoJo(1, 2, 3)//создали экземпляр
        val copyFoo = foo.copy(c = 15)//тот же экземпляр, только поменяли C
    }

    @JvmInline
    value class Name(val name: String) {//класс который принимает только 1 свойство и делает
        // из него тип Name(получили новый тип данных без нагрузок на процесс)

        fun someMethod(): String {
            return name
        }

        fun someMethod2() {}
    }

    interface MyInterface {
        fun defaultFun(): Int = 4//дефолтная реализация,необязательно переопределять
        fun myFunc()
    }

    object MyObject : MyInterface {
        //синглтон,могут реализовывать интерфейсы и классы
        override fun myFunc() {}//синглтон,могут реализовывать интерфейсы и классы

        ////////if
        fun ss() {
            val value: Any = ""//any = object в джава
            if (value is String) {//is проверка на тип
                value as? String//as = кастануть,если вернет нулл ,то не упадет
                val stringLength = (value as? String)?.length ?: 0//вернуmь длину строки .Если это
                // не кастанется к строке и вернет null,то 0
            }
            val result = if (value == 4) {
                "four"
            } else {
                "not Four"
            }//иф работает как функция и возвращает значение
        }
    }

    val lazyVal: PoJo by lazy { PoJo(3, 3, 3) }//инициализируется после обращения к этому полю
////////////////модификаторы
//private - только данному классу
//protected - только наследникам
//internal - внутри модуля
//public - по умолчанию,видно везде

    ///////////циклы
/////////////////Списки
    fun lll() {
        //от 0 до 40
        for (i in 0 until 40) {
        }

        val list = listOf(1, 2, 3, 4)
        list.forEach {
            print(it)
        }
        list.forEachIndexed { index, i ->
            if (index > 1) {
                print(i)
            }
        }//получаем и интекс и значение

        val newList = list.map { it + 1 }//создали лист у которого все значения на 1 больше
        newList.filter { it > 0 }//вернет все больше 0
        newList.indexOfFirst { it == 1 }//вернет индексы чисел с первой цифрой 1
        newList.first { it % 2 == 0 }//вернет первый элемент который делится на 2

        hashMapOf<String, Int>()//мапы ключ знаение
        setOf<String>()//сет
    }

    /////////////////
    companion object : MyInterface {
        //статик поле которое может реализовывать интерфейсы
        override fun myFunc() {
            TODO("Not yet implemented")
            Sint.Companion.myFunc()//как вызывается метод компанион обжекта
        }
    }

    /////////////////скоп функции
//возвращает сам фрагмент
    val fragment = Fragment().apply {
        //здесь возвращается бандл
        arguments = Bundle().apply {
            putString("1", "adfs")
        }
        ///let
        val someField: Int? = 323
        someField?.let {
            //вернет результат лямбды(328)
            it + 5//выполнится только если не нулл
        }
        ////also
        fun launchActivity(context: Context) {
            Intent(context, MainActivity::class.java).also {
                context.startActivity(it)
            }

            ////with
            val textView: TextView = TextView(context)
            with(textView) {
                text = "ssss"
                textSize = 15f//результат лямбды
            }
            //////////////////
            textView.run {
                text = "text"//вернет результат лямбды
            }
        }

    }
}





//////////////////////////////////////////////////////////старые лекции


class commands constructor(var str: String?, val str2: String) {
    //конструктор(если конструктор 1,то слово конструктор можно убрать)
    //добавить котлин в проэкт джава
    //Tools-Kotlin-Configure Kotlin in project
/////////////////
    constructor(otherString: String) : this(otherString, "")//второй конструктор(в котором только один входящий параметр)

    /////////
    init {//вызывается после создания класса
        val str2 = "init String"
        str = null//нельзя чтоб она была нул, если не стоит ?(String?)
        str!!.length//!! говорит о том что принимает нулл и если там будет нулл то приложение упадет,проверяться не будет
        val value:Int? = str?.length//если получим нулл,операция не выполнится
        val value2 = str?.length ?: 0//если операция не выполнилась,возвращаем 0(?: как тернарный оператор)
    }

    companion object Asd123{//типа поля статики//пожет реализовать интерфейс:MyInterface(можно и без имени создавать(Asd123))
        //fun newInstance() = Fragment()
//класс может унаследовать один интерфейс а компаньон обджект  другой
    }


    /////////////
    //val - неизменяемое
    //var - изменяемое
    lateinit var initString: String// - инициализировать позже
    //typealias newString = String//теперь вместо стринг можно писать newString
    var someStr:String=""
    private set//запретить у поля someStr вызывать сеттер вне этого класса

    val strLength: Int
        get() = str2.length//переменная будет возвращать длину стринг 2,вычисляется значение при каждом запросе ,это как get(){ return str2.length();}
    val strLength2:Int = str2.length//это уже запишет 1 раз значене переменной и все

    var setCoutumeGetterSetterField:String=""//////////устанавливааем геттеры и сеттеры свои(если ничего не писать останутся дефолтные)
        set(value){//метод для того чтоб установить значение поля
            field = value + "какая-то строка которая всегда добавлятся"//field- обращение к полю под которым этот метод
        }
        get(){return field.filter { it.isDigit() }}//например выводить только цифры
    fun iss(){
        if(str2 is String){//если стр 2 это стринг то
            str2 as Int//кастануть стр 2 к инту

        }
    }
///////////////

    fun myMethod(Str1: String="default string"): String {//методы в котлине
        if (::initString.isInitialized) {
        }//если строка проинициалищированна ,то выполняем
        fun doSomething():Int = 4//функфия внутри функции,вызывается только внутри функции
        doSomething()
        return ""
    }

    fun methodName(arg1:String="str",callback:((arg1:Int,arg2:Int)->String)){//callback- это поле принимает в качестве аргумента любую функцию с 2 аргументами,которая возвращает стринг
     //тоесть функция методНейм принимает в каачестве аргемента функцию,у которой 2 аргумента инта и тип возвращаемого значения стринг
    //////////вызываем функцию каллбек внутри функции(так же можем указать дефолтное значение нашей функции каллбек"= defaultFunc()")
        callback.invoke(1,2)//или
        callback(1,2)//одно и тоже
    }
    fun forUse(){
        val a = "123123123"
        methodName("asd",{a,b-> a.toString() + "str"})//вызываем нашу функцию с функцией в качестве аргумента()
    }

}

val someCommands = commands("myStr", "myStr2")//создали экземпляр класса и сохранили его в переменную


/////////КЛАССЫ private-виден только внутри себя,protected-виден наследникам,internal-виден внутри модуля,public-по умолчанию
//////ОПЕН (ПО УМОЛЧАНИЮ ВСЕ ЗАКРЫТО В КОТЛИН)
open class Class1 {///класс от которого можно наследоваться(open)по умолчанию нельзя
    open fun function():Int = 4//функция open,чтобы мы могли оверрайдить метод у наследников(возвращает 4)
    open val isOk:Boolean//свойство,сделали опен чтоб могли его переопределить,просто возвращает тру
    get() = true

    inner class innerClass(val x:Int,val y:Int){//указываем иннер(вложенный класс)чтоб были доступты все свойства класса,в котором он лежит
        fun innerFunc():Boolean = isOk//например isOk
    }
}
class class2 : Class1() {
    override fun function(): Int=10//возвращает 10 ,так выглядит переопределенный метод
    override val isOk: Boolean
        get() = false//теперь он возвращает фолс
}
//Value class
@JvmInline
value class namee(val value: String)//это класс-свойства,можно определять методы и вычесляемые свойства(через метод get()которые),но обычные свойства нельзя

//Data- является файналом и по умолчанию имеет методы хэшкод и иквелс,от него нельзя наследоваться
data class DataClass(val x:Double,val y:Double,val z:Double=0.5){//указали в конструкторе значение по умолчанию для z=0.5
    fun dataMethod(){}
}
    fun dataFunc(){
        val foo = DataClass(x=10.6,y=5.3,z=0.0)//создали экземпляр дата класса
        foo.dataMethod()//вызвали метод нашего класса
        val copyOfFoo = foo.copy(x=10.10)//копирует все свойства дата класса,кроме тех,значение которых мы указали

        val lazyINit:DataClass by lazy { //ленивая инициализация,проинициализируется,когда кто-то обратися к этому класса
            DataClass(1.1,1.1,1.1)
        }
    }
val lazyValue:DataClass by lazy {//отложенная инициализация класса
    //делаем код
    DataClass(1.3,2.4,3.3)//инициализируем(проинициализируется,когда мы впервые обратимся к этому классу)
}
//обжект = Singletone - Клаасс, который имеет всего 1 экземпляр и все
object MyObjClass {//конструктор невозможет,но могут быть поля и методы.тАК ЖЕ МОЖНО РЕАЛИЗОВАТЬ В НЕМ ИНТЕРФЕЙС
    var x = 0;
}

fun ForObj(){MyObjClass.x }//вызываем свойство синглтона
//value класс value class(val value:String)//в этом классе мы храним только одно свойство,можем определятб методы и вычисляемые свойства
//////////////интерфейсы
interface myInterface{
    fun method()
    fun methodTop(): Int=4//дефолтная реализация,возвращает сразу 4
    val value1:String//обявлять поля ожно в интерфейсе сразу
    var variable1:String//и прийдется все это переопределить в классе
}
    //анонимный класс
var textWatcher= object :TextWatcher{//как реализовывать анонимный интерфейс
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            TODO("Not yet implemented")
        }


        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            TODO("Not yet implemented")
        }

        override fun afterTextChanged(s: Editable?) {
            TODO("Not yet implemented")
        }
    }
///////Всякие операторы котлина
val someObj: Any = "string"//any аналог Object в Java(если мы не знаем какой это тип данных)

val casted: String = someObj as String //кастуем someObj в String

//val castedSoft: String? = someObj//если это стринг то присвоить

val length = (someObj as? String)?.length ?: -1

val isString = someObj is String

////////////////ЦИКЛЫ
fun cycle(){
    for(i in 1..10) {//от 1 до 10
        print("Hello Kotlin!")
    }
    for(i in 10 downTo 1 step 2) {//от 10 до 1 ,шаг 2
        print("Hello Kotlin!")
    }

    for (i in 0 until 3) {//до 3
    }
}

//всякие итерации и тд
val strr:String = "asasd"
val result = if(strr.length == 4){"4" }else "не 4"//if возвращает последнее значение которое в конструкции
fun cc(){
    when(result){//оператор вен = свич в джаве,но не нужны брейки
        "one"->{"do it"}
        "two"->{"do this"}
        else->{"do else"}//если не то и не то,делается элс
    }
    for (i in 0 until 10){}//от 0 до 10 пройтись
    while (true){}
    val list= listOf<String>("one","two","three")//дженерик можно убрать,спиисок только для получение элементов
    val list2 = mutableListOf<String>("a","b","c")//можно менять удалять добавлять и тд

    list.forEach{
        print(it)//напечатает список
    }

    list.forEachIndexed{index, s ->  }//перечислить с индексами

    val length123:List<Int> = list.map { it.length }//получить длины каждого элеменда и все добавить в новый лист
    length123.filter { it!=0 }//вернет те которые не равны 0

}

