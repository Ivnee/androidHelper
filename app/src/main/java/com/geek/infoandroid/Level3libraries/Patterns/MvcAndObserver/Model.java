package com.geek.infoandroid.Level3libraries.Patterns.MvcAndObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {//обсервалбл , класс на который подписываются обсерверы
    private List<Integer> mList;
    public Model(){
        mList = new ArrayList<>(3);
        mList.add(0);
        mList.add(0);
        mList.add(0);
    }
    public int getElementValueAtIndex(int _index){
        return mList.get(_index);
    }
    public void setElementValueAtIndex(int _index){
        int currentValue = mList.get(_index);
        mList.set(_index, currentValue + 1);
        setChanged();//Метод обсервера (УСТАНАВЛИВАЕТ ИЗМЕНЕНИЯ)
        notifyObservers();//метод обсервера(сообщает всем подписчикам , что были изменения в жтом классе)ВЫЗЫВАЕТ МЕТОД UPDATE
    }

}
