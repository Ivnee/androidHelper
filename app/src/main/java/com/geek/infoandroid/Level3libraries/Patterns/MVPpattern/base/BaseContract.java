package com.geek.infoandroid.Level3libraries.Patterns.MVPpattern.base;

public interface BaseContract {
    //implement в мейн контракте,здесь методы которые нужно будет описать
    interface View {
    }
    interface Presenter<V extends BaseContract.View> {//для обозначение презентера
        void attachView(V view);
        void detachView();
    }
}
