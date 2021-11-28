package com.geek.infoandroid.android.LiveDataViewModel.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {//еще есть AndroidViewModel, в которой сразу есть апликейшн Контекст
    MutableLiveData<String> liveData;
    private final int id;

    public MyViewModel(int id) {
        this.id = id;
    }

    public LiveData<String> getData(){//метод получения лайвдаты для активити(фраг и тд)
        if(liveData == null){
            liveData = new MutableLiveData<>();
            //здесь мы получаем какие-то данные и сетим их в лайвдату(загружать данные нужно в отдельном потоке)
            liveData.postValue("get data from reposetory");
        }
        return liveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        //метод для отчистки ресурсов
        //срабатывает при уничтожении вью модел,здесь закрываем все процессы
    }
}
