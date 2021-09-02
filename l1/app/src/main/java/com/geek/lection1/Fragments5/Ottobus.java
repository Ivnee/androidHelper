package com.geek.lection1.Fragments5;

import com.squareup.otto.Bus;

public class Ottobus {
    static private Bus bus;//создаем наш бас

    public static Bus getBus(){//получить бас,если его нет то создаем
        if(bus == null){
            bus = new Bus();
        }
        return bus;
    }
}
