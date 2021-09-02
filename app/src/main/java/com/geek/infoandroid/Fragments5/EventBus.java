package com.geek.infoandroid.Fragments5;

import com.squareup.otto.Bus;

public class EventBus {
    private static Bus bus;//импортируем бас и создаем для него синглтон


    public static Bus getBus(){
        if(bus == null ){
            bus=new Bus();
        }
        return bus;//в активити подписываемся в методе onStart() отписываемся в onStop();
    }
}
