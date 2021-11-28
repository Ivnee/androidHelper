package com.example.testcode.operators

import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Qualifier

class Qualifier {//создаем свои аннотации вместо @Named
    @Qualifier//создаем аннотацию прод
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Prod(val value :String = "")//можем добавить аргумент в свою аннотацию

    @Qualifier//создаем аннотацию Dev
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Dev

    @Module
    class QualifierModule{
        @Dev//созданная нами аннотация
        @Provides
        fun provideSomeApiDemo(): Named123.SomeApi {
            return Named123.SomeApi("demo.server.ru")
        }

        @Prod("1")//чтоб можно было несколько версий прод сервера делать
        @Provides
        fun provideSomeApiMain1(): Named123.SomeApi {
            return Named123.SomeApi("main1.server.ru")
        }
        @Prod("2")//вторая версия прод сервера
        @Provides
        fun provideSomeApiMain2(): Named123.SomeApi {
            return Named123.SomeApi("main2.server.ru")
        }
    }
    @Inject
    @Prod("1")//создаем какой - то апи ,вариант прод
    lateinit var someApiMain: Named123.SomeApi

}