package com.example.testingdagger2

import android.app.Application
/*
@Inject — указывает на метод, конструктор или поле класса, которые (или в которые) нужно что-то внедрить;
@Provides — указывает на метод, который предоставляет (возвращает) зависимость для дальнейшего внедрения;
@Module — обозначает класс-«контейнер» с набором методов, которые отмечаются аннотациями @Provides;
@Component — отмечает интерфейс, который позже будет использоваться для генерации кода. В нем определяют,
куда можно что-либо внедрять, и, возможно, методы для прямого доступа к зависимостям.*/
/////НУЖНО ДОБАВИТЬ В МАНИФЕСТ
class App : Application() {

/*
    1) Build -> Clean project

    2) File -> Invalidate cache and restart -> Invalidate and restart*/
    //val appComponent = DaggerAppComponent.create()//если рокидывать ничего не надо

    val appComponent = DaggerAppComponent.builder().autoDependModule(//если нужен модуль
        AutoDependModule(
            Depend1(), Depend2()
        )
    ).build()
}