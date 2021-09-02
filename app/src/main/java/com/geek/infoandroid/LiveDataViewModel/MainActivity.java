package com.geek.infoandroid.LiveDataViewModel;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import com.geek.infoandroid.R;

public class MainActivity extends AppCompatActivity {
    TextView myTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myServerMethod();
        liveDataController();
        liveDataTransformation();
        liveDataTransformUser();
        mediatorLiveData();


    }

    private void myServerMethod() {
        MyServer myServer = new MyServer();//создаем класс наплюдатель за лайфциклом
        getLifecycle().addObserver(myServer);//получаем лайфцикл и добавляем экземпляр наблюдателя
        getLifecycle().removeObserver(myServer);//отписаться от наблюдателя
        if(getLifecycle().getCurrentState()== Lifecycle.State.RESUMED){//если состояние активити = resume ,то ...
            if(getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)){}//состояние активити не ниже он стартед,т.е. либо стартед либо ресум
        }
    }

    private void liveDataController() {
        LiveData<String> liveData= DataController.getInstance().getData();
        //будет отправлять только когда состояние активити активное ,т.е. STARTED -> RESUMED
        liveData.observe(this, new Observer<String>() {//методом обсерв подписываемся на лайфдата,первый параметр это LifeCycleOwner(все активити и фрагменты наследуются от этого интерфейса)чтоб лайфдата получила нашу активити
            // 2 это подписчик(коллбэк) в который лайфдата будет отправлять данные
            @Override
            public void onChanged(String s) {
                myTextView.setText(s);
            }
        });
        liveData.hasActiveObservers(); //- проверка наличия активных подписчиков
        liveData.hasObservers();// - проверка наличия любых подписчиков
       // liveData.observeForever (Observer<T> observer);// - позволяет подписаться без учета Lifecycle. Т.е. этот подписчик будет всегда считаться активным.
        //liveData.removeObserver (Observer<T> observer) - позволяет отписать подписчика
        //liveData.removeObservers (LifecycleOwner owner) - позволяет отписать всех подписчиков, которые завязаны на Lifecycle от указанного LifecycleOwner.
    }

    private void liveDataTransformation() {//меняем тип данных у лайфдата методом трансформейшнс
        LiveData<String> liveDataStr = DataController.getInstance().getData();
        LiveData<Integer> liveDataInt = Transformations.map(liveDataStr, new Function<String, Integer>() {//можно сказать что он подписан на liveDataStr и все его подписчики теперь будут получать инты
            @Override
            public Integer apply(String input) {//данные из liveDataStr
                return Integer.parseInt(input);//данные которые вернутся в liveDataInt
            }
        });
    }

    private void liveDataTransformUser() {//для смены типа данных более сложный вариант
        LiveData<Long> longLiveData = DataController.getInstance().getLongData();
        LiveData<Student> studentLiveData = Transformations.switchMap(longLiveData, new Function<Long, LiveData<Student>>() {
            @Override
            public LiveData<Student> apply(Long input) {
                return getStudentById(input);//получаем нашего студента по номеру(допустим для базы данных)
            }
        });
    }


    private void mediatorLiveData() {//получаем данные из обоих лайв дат в метиатор и из медиаторв в нашего подписчика
        new MyMediatorLiveData().mediator.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                System.out.println("setText()" + s);
            }
        });
    }

    //типа здесь запрос в базу
    private LiveData<Student> getStudentById(Long input) {
        return null;
    }
}