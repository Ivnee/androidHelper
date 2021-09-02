package com.geek.infoandroid.LiveDataViewModel;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;

public final class DataController implements LifecycleObserver {//синглтон с Лайфдатой
    private MutableLiveData<String> liveData = new MutableLiveData<>();//создаем нашу лайфдату
    private MutableLiveData<Long> longLiveData = new MutableLiveData<>();//создаем лайвдату лонг

    private static DataController instance;
    public static DataController getInstance(){//наш синглтон
        if(instance==null){
            instance = new DataController();
        }
        return instance;
    }
    public LiveData<String> getData(){//наружу мы передаем только лайфдату , которая позволит внешним объектам только получать данные
        return liveData;
    }
    public LiveData<Long> getLongData(){
        return longLiveData;
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void setMyValue(){
        liveData.setValue("on start");//обновит состояние лайфдаты,все подписчики получат его(если в активном состоянии)(вызывается в UI потоке)
        liveData.postValue("post on start");//для использования В любом потоке,он перенаправит в UI поток
    }
}
