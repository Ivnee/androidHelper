package com.example.testcode.operators

import javax.inject.Inject

class Inject2 {
    class Dog @Inject constructor()//@Provides метод в модуле больше не нужен

    /*Объект созданный этим способом:
    - может быть получен от компонента get-методом
    - может быть заинджектен компонентом
    - может быть использован как аргумент в Provides методе создания другого объекта*/
    class Cat @Inject constructor(dog: Dog)//если даггер знакт как создать аргументы такого класса,то он их создаст сам


    //Если в конструкторе необходимо получить объект с аннотацией Named или Qualifier, то просто указывайте ее перед объектом
    class Animals(
        private val cat: Cat,
        private val dog: Dog,
        @Qualifier.Dev private val someApi: Named123.SomeApi
    )//получаем реализацию someApi с ссылкой Dev

    class Cat2 @Inject constructor(private val dog: Dog) {//если класс создается не инжектом,а методом из модуля,метод инит не вызовется при создании
        @Inject//когда даггер буудет создавать этот объект,он сразу вызовет этот метод
        fun init(cat: Cat) {//и даггер может передать в него аргументы ,если они у него есть
            //Важно. Если даггер создает объект с помощью Provides метода из модуля, а не создает сам Inject конструктором, то
            // Inject методы вызваны не будут.
        }
    }
    class MainActivity(){
        //(application as App).appComponent.injectMainActivity(this)//Если мы инжектим активити или любой другой класс
        @Inject
        fun init(){}//все его инжект методы будут вызваны

    }
}
