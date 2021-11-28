package com.geek.infoandroid.android.LiveDataViewModel.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ModelFactory extends ViewModelProvider.NewInstanceFactory {//класс для передачи данных в конструктор ViewModelи
    private final int id;//параметр который мы хотим передать во  втю модель конструктор

    public ModelFactory(int id) {//конструктор
        super();
        this.id = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {//метод вью модел фактори
        if(modelClass == MyViewModel.class){//если это модель которая нужна нам,то возвращаем ее
            return (T)new MyViewModel(id);//и это мы будем вставлять в активити
        }
        return null;
    }
}
