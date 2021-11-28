package com.example.testcode

import android.app.Application

//если даггер компонент не создается ( Build -> Make Project (CTRL+F9). )
//если выдает ошибки,когда все норм
//1) Build -> Clean project
//2) File -> Invalidate cache and restart -> Invalidate and restart
//3)поменять 1 буква в классе,который не генерится,и вернуть обратно
class App : Application() {
    val appComponent = DaggerAppComponent.create()

    //если нужно прокинуть зависимости в компонент(например контекст)
    val appComponent2 = DaggerAppComponent.builder()
            .moduleApp(ModuleApp(this))
            .build()
}