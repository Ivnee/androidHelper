package com.geekbrains.lesson1.clear.presentation.main;

import com.geekbrains.lesson1.clear.domain.model.Article;
import com.geekbrains.lesson1.clear.domain.usecase.ArticleInteractor;

import java.util.List;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

public class    ClearViewModel extends ViewModel implements LifecycleObserver {//обсервер для слежки за жизненным циклом нашего приложения

    private ArticleInteractor interactor;

    public MutableLiveData<List<Article>> articleLiveData = new MutableLiveData<>();

    public ClearViewModel(ArticleInteractor interactor) {
        this.interactor = interactor;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)//указываем что метод выполняется ,когда в активити ,в которой добавлен этот класс getLifecycle().addObserver(ClearViewModel(т.е. наш клас обсервер))срабатывает метод он старт
    public void onStart() {
        new Thread() {
            @Override
            public void run() {
                List<Article> list = interactor.getArticles();
                articleLiveData.postValue(list);
            }
        }.start();
    }
    ////////////////дополнительно
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)//реашируеь на все состояния активити ,на которую мы подписаны
    void onAny(LifecycleOwner source, Lifecycle.Event event) {
        boolean isStarted = source.getLifecycle().getCurrentState() == Lifecycle.State.STARTED;//получить текущее состояние активити и сравнить с состоянием он старт
    }
}
