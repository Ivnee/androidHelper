package com.geek.infoandroid.Level2.les3.Services;

public class ServiceLifeCycle  {
    /*
    запущенный
    onCreate();//создает сервис
    onStartCommand();//здесь пишем код для работы
    ...
    stopSelf();//останавливает сервис в котором был вызван
    stopService(new Intent(this,MyService.class);//если хотим завершить из вне
    привязанный
    onCreate();//привязываемся к сервису
    onBind();//здесь пишем код нашего для работы
    onUnbind();//когда клиент отключился от сервиса
    ...

        */

////////////////////////////////////////////////////////////////////////
    //тоже самое только отдельно привязанный сервис и запущенный сервиси
    /*

//запущенный(просто для обработки данных)

    onCreate();
    onStartCommand();
    //работает
    //stopSelf();//в самом сервисе ,сам себя убьет//или stopService(new Intent(this,MyService.class);
    onDestroy

/////привязанный(для того чтоб можно было получить инфу от сервиса)
    onCreate()
    onBind();//привязываем
    //работает
    onUnbinde();отвязываем
    onDesstroy
    */
}
