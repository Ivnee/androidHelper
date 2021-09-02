package com.geek.lection1.Fragments5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.geek.lection1.R;

public class Fragment1 extends Fragment {
    TextView textView;
    @Nullable
    @Override//создает врагмент
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1_layout,container,false);//подключаем лейаут
        // (нашли лейаут,добавили вьюхи(приходят на вход в виде аргументов),привязка к парент активити(почти всегда фолс))
    }

    @Override//в этом методе идет работа с вью и их определение
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);//не терять данные при повороте
        textView = view.findViewById(R.id.fragmentText);//находим вьюхи через view.
        if (getActivity() instanceof ActivityForFragment) {//проверяем что наша активити это активити фрагмента
            ((ActivityForFragment) getActivity()).fragmentCommands();//запускаем из нее метод для работы с другими фрагментами или любым кодом
        }
        ottobus();
        listView(view);
    }

    private void ottobus() {
        //подключаем оттобас в гредл
        //implementation 'com.squareup:otto:1.3.8'
        String str = "container = new Container и сетим в его поля все нужные данные ";
        Ottobus.getBus().post(str);//вставляем сюда контейнер с данными
    }
    //создаем лист вью
    private void listView(View view){
        ListView listView = view.findViewById(R.id.listView);
        ArrayAdapter adapter = ArrayAdapter.createFromResource
                (getActivity(),R.array.array,android.R.layout.simple_list_item_activated_1);//адаптер массива для лист вью,1 контекст 2 массив значений для листа3стандартный лейаут для каждого элемента нашего списка
        listView.setEmptyView(textView);//устанавливаем как будут отображаться пустые элементы списка
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);//подсвечивает выбраный элемент
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//устанавливаем листенер на каждый элемент лист вью
            @Override
            public void onItemClick(AdapterView<?> parent, View view1, int position, long id) {//получаем при нажатии позицию элемента
                int currentPosition = position;
            }
        });
    }

}
