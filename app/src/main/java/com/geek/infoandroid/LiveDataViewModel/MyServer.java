package com.geek.infoandroid.LiveDataViewModel;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

public class MyServer implements LifecycleObserver {//указываем что этот класс будет читай чей-то жизненный цикл(подписываем в активити или фрагменте)

    @OnLifecycleEvent(Lifecycle.Event.ON_START)//этот метод будет срабатывать когда вызывается он старт
    public void connect(){

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)//этот когда вызывается он стоп
    public void disconnect(){

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)//когда вызывается любой метод лайфцикла
    public void allStates(LifecycleOwner source, Lifecycle.Event event){//event,входящий параметр чтоб определить какое событие имнно произошло(посмотреть как)
    }

}
