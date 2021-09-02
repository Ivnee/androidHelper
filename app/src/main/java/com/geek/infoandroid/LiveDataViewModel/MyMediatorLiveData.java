package com.geek.infoandroid.LiveDataViewModel;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class MyMediatorLiveData {
    MutableLiveData<String> liveData1 = new MutableLiveData();
    MutableLiveData<String> liveData2 = new MutableLiveData();

    MediatorLiveData<String> mediator = new MediatorLiveData<>();

    public MyMediatorLiveData() {//когда мы подпишимся на медиатор в активити, к нам будут приходить данные с лайф дата 1 и 2
        mediator.addSource(liveData1, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mediator.postValue(s);//или
                mediator.setValue(s);//или
            }
        });
        mediator.addSource(liveData2, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if("finish".equalsIgnoreCase(s)){//это если нам нужно отписать лайвдату от медиатора при отпрвике финишь
                    mediator.removeSource(liveData2);
                }
                mediator.postValue(s);
            }
        });
    }
}
