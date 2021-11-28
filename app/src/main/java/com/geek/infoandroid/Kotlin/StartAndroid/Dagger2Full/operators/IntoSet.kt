package com.example.testcode.operators

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet
import dagger.multibindings.LongKey
import dagger.multibindings.StringKey
import javax.inject.Inject

class IntoSet {
    interface Animals{}
    class Cat():Animals{}
    class Dog():Animals{}

    @Module
    class ModuleSet{
        @IntoSet//узазывает на то что даггер должен собрать объекты,помеченные этой аннотацией в сет
        @Provides
        fun provideCats():Animals{//тип возвращаемого значения должен быть общий
            return Cat()
        }

        @IntoSet
        @Provides
        fun provideDogs():Animals{
            return Dog()
        }
        //IntoMap аналог IntoSet но с ключем
        @IntoMap//даггер соберет объекты в мапу
        @StringKey("Cat")//ключ к этому элементы в мапе(может предоставить лонг инт и тд)
        //@LongKey
        @Provides
        fun provideCatsMap():Animals{//тип возвращаемого значения должен быть общий
            return Cat()
        }
    }
//Component в компоненте создадим метод который все это соберет
    interface component123{
    //Через инжект не получится сделать сет и мап, только через методы
        fun getAnimals():Set<Animals>//вернет сет из всех классов энималс, у которых есть аннотация IntoSet
        fun getAnimalsMap():Map<String,Animals>
    }
}
//еще есть ElementsIntoSet ,он добавит элементы уже в полученный сет,если пригодится ,поискать инфу по нему