package com.example.testingdagger2

import com.geek.infoandroid.Kotlin.les8Room.DatabaseModule
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton

@Singleton//чтоб заработал синглтон нужно пометить наш компонент и методы модулей с
// зависимостями(эта аннотация делает чтоб зависимости были в единственном экземпляре,даже если будем
// вызывать билд несколько раз)
@Component(modules = [AutoDependModule::class,DatabaseModule::class])//указываем чтоб компонент
// передал зависимости(Depend1,Depend2)нашему АутоДепендМодулю(с кастомными эклемплярами классов Depend1..2)
interface AppComponent {




    //зависимость которую хотим открыть
    fun getAuto(): Auto//открытая зависимсть,через ссылку на этот компонент можем получить нашу зависимость(посмотрит что MyClass помечен аннотацией инжект и добавит этот компонент в мэйнактивити)

    //указали какой именно депенд 2 нам нужен
    @Named("named2") fun getDepend2():Depend2


    //или указываем просто кому мы предоставляем все эти зависимости и вызыв этих зависимостей будет гораздо короче
    fun inject(mainActivity: MainActivity)
}