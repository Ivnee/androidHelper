package com.geek.lection1.Fragments5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.geek.lection1.R;
import com.squareup.otto.Subscribe;

public class ActivityForFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_fragment);
        fragmentCommands();
    }

    public void fragmentCommands() {
        Fragment2 fragment2 = new Fragment2();//создаем экземпляр второго фрагмента
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();//функция для замены фрагментов в контейнерах
        transaction.replace(R.id.fragment1Container,Fragment2.create("sdasd"));//вставляем второй фрагмент в контейнер первого(вставляет в бэкстек предыдущий фрагмент)
        transaction.remove(fragment2);//удалить фрагмент
        transaction.add(R.id.fragment2Container,fragment2);//вставляем фрагмент 2 в контейнер (если контейнер пустой,то адд,он не добавляет бэкстек)
        transaction.addToBackStack(null);//или someKey , добавляет фрагмент в стек обратного вызова(ключ для того чтоб маожно было найти по нему)
        getSupportFragmentManager().popBackStack("someKey",123);//флаг хз зачем, ключ для вывода помеченного бэкстека
        transaction.commit();//устанавливаем изменения
        Fragment1 findFragment = (Fragment1) getSupportFragmentManager().findFragmentById(R.id.fragment1Container);//находим фрагмет по айди контейнера(контейнер находится в мейн активити)
        //упрощенно
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment1Container,fragment2);
    }

    @Override
    public void onBackPressed() {//срабатывает при нажатии назад
        super.onBackPressed();
        System.out.println("нажал назад ");
        int chisloOconVbackStack = getSupportFragmentManager().getBackStackEntryCount();//колво окон в бэкстеке(if > 0 ,то делаем что-то)
    }

    //////////////////OTTOBUS
    @Override
    protected void onStart() {
        super.onStart();
        Ottobus.getBus().register(this);//зарегестрировать оттобас на уведомления
    }
    @Subscribe
    public void useOttobasMethod(String containerClass){//метод принимает класс с данными
        //получаем контейнер с данными из фрагмент 1 и работаем с ними

    }

    @Override
    protected void onStop() {
        Ottobus.getBus().unregister(this);//cнять регистрацию
        super.onStop();
    }
}
