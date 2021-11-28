package com.geek.infoandroid.android.Level3libraries.Patterns.MVPpattern;

import com.geek.infoandroid.android.Level3libraries.Patterns.MVPpattern.base.BaseContract;
//можно создать 2 отдельных интерфейса,закинули в 1 интерфейс для удобства
interface MainContract {
    interface View extends BaseContract.View {//методы которые обязяна выполнить наша вью(активити)
        void setSeconds(int value);
        void setMinute(int value);
        void setHours(int value);
    }
//презентр должен реализовать 3 метода и наследуется от Presenter, который должен получить вью(для аттача и детача)
    interface Presenter extends BaseContract.Presenter<View> {//методы ,которые должен выполнить презентр(MainPresenter)
        void incSec();
        void incMin();
        void incHours();
    }
}
