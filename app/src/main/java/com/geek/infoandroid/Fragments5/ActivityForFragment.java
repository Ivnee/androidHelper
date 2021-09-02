package com.geek.infoandroid.Fragments5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.geek.infoandroid.R;
import com.squareup.otto.Subscribe;

public class ActivityForFragment extends AppCompatActivity implements Fragment1.DoFromFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_fragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();//получаем транзакцию для смены фрагментов в контейнерах
        Fragment2 fragment2 = new Fragment2();
        transaction.replace(R.id.layoutForFragment,fragment2);//заменяем то что было в контейнере на фрагмент 2
        transaction.add(R.id.layoutForFragment,fragment2);//для пустого контейнера(нет бэкстека) добавляем фрагмент в контейнер
        transaction.remove(fragment2);
        transaction.commit();//осуществить действие с транзакциями
    }

    @Override
    protected void onStart() {
        super.onStart();
//зарегестрировали
        EventBus.getBus().register(this);//подписываемся для получения событий от фрагмента
    }
//что-то сделали
    @Subscribe//подписываем метод для вызова из других мест с помощью оттобаса
    public void doSomethingWithBusData(BusData busData){//метод должен быть публичным
        String name = busData.name;//получаем данные из класса контейнера для ивентбаса
        int age = busData.age;
    }
    @Override
    protected void onStop() {
        //отписались
        EventBus.getBus().unregister(this);//отписываемся
        super.onStop();

    }

    @Override
    public void doFromFragment(String str) {//этот метод вызывается из фрагмента 1 через интерфейс
        //что - то делаем в этом методе с фрагментом2 или с самой активити
    }
}