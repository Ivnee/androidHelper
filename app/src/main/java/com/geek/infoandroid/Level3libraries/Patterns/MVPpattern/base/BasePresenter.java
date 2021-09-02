package com.geek.infoandroid.Level3libraries.Patterns.MVPpattern.base;
// в дженерик приходит любой тип (класс),который  является наследником(имплементит) BaseContract.View .И реализовать методы презентора
//класс для реализации детач и аттач вью
public class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter<V> {
//это вещь вообще необязательна,сделано для того , чтоб каждому презентору не писать эти методы,просто вынесли в отдельный итерфейс
    protected V view;

        @Override
        public void attachView(V view) {
            this.view = view;
        }

        @Override
        public void detachView() {
            view = null;
        }
}
