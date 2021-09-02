package com.geek.infoandroid.Fragments5;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.geek.infoandroid.R;

public class Fragment1 extends Fragment {
    ListView listView ;
    //взаимодействие между фрагментами происходит через активити
    public interface DoFromFragmentListener{//интерфейс для взаимодействия с активити (с помощью активити посылаем действие во фрагмент 2)
        public void doFromFragment(String str);//метод ,который реализуем в активити для действий над фрагментом 2
    }
    DoFromFragmentListener doFromFragmentListener;//экземпляр нашего активити который имплементит интерфейс данного фрагмента

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        doFromFragmentListener = (ActivityForFragment)context;

        //вызов метода который переопределен в активити
        doFromFragmentListener.doFromFragment("данные для обработки в активити");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,container,false);//сразу сохраняем во вью фрагмент с лейаутом
        view.findViewById(R.id.fragmentTextView2);//поиск вью во фрагментах
        setRetainInstance(true);//не пересоздает фрагмент при повороте экрана
        return inflater.inflate(R.layout.fragment1,container,false);//подсоединили хмл к фрагменту и вернули вью
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {//вью это основной наш контейнер,в котором лежит все из фрагмента
        super.onViewCreated(view, savedInstanceState);
        getActivity();//получить активити на которой находится фрагмент(можно вызывать методы активити таким образом)
        EventBus.getBus().post(new BusData());//вызываем то что подписано на евентбас и отправляем туда класс контейнер

        TextView textView = view.findViewById(R.id.textViewForList);//текст вью которая будет указываться в пустых слотах листа
        listView = view.findViewById(R.id.list_view);//находим лист вью
        ArrayAdapter adapter =  ArrayAdapter.createFromResource(getActivity(),R.array.array, android.R.layout.simple_list_item_activated_1);//СОЗДАЕМ АДАПТЕР,ВСТАВЛЯЕМ ТУДА МАССИВ СТРИНГОВ И ДАЕМ СТАНДАРТНУЮ РЕАЛИЗАЦИЮ СПИСКА
        listView.setAdapter(adapter);//устанавливаем данный адаптер со стандартной реализацией и со своей текст вью
        listView.setEmptyView(textView);// установили текст вью для пустых слотов
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //position - выдает позицию элемента ,на который мы нажали
                listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);//когда выбран элемент оставлять его подсвеченым
            }
        });
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();//новый менеджер для транзакций(позваляет распалагать фрагменты внутри нашего фрагмента)
        FragmentTransaction ft2 = getParentFragmentManager().beginTransaction();//начать транзакцию фрагментов(в любом месте помоему)
        ft.replace(R.id.layoutForFragment,new Fragment2(),"tag");//заменяем фрагмент в контейнере(тэг не обязателен)
        Fragment fff2 = getFragmentManager().findFragmentByTag("tag");//найти фрагмент по тэгу
        ft.commit();
    }
}
