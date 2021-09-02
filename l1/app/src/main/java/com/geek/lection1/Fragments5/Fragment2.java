package com.geek.lection1.Fragments5;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.geek.lection1.R;

public class Fragment2 extends Fragment {
    public static Fragment2 create (String dannieDlyaSozdania){//статический метод инициализации,он создает фрагмент с данными которые принимает метод
        Fragment2 fragment2 = new Fragment2();
        Bundle forData = new Bundle(); //андроид контейнер для аргументов
        forData.putString("sss",dannieDlyaSozdania);//передаем данные
        fragment2.setArguments(forData);//вставляем аргументы бандла в наш фрагмент
        return fragment2;//возвращаем фрагмент с аргументами
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment2_layout,container,false);

    }
    public void commands () {
        getArguments().getString("sss");//получить строку
        getArguments().getStringArrayList("1");//получить аррайлист из строк
        getArguments().getSerializable("sssss");//получить класс контейнер или вообще любой класс (перед гет сериалайз кастуем к классу(Container))
        TypedArray typedArray = getResources().obtainTypedArray(R.array.imgs);//берет массив с любым типом значений,getRes- достать из файловыых ресурсов
        int znachenieIzMassiva = typedArray.getResourceId(0,-1);//достаем значение из массива по индексу,0 которое мы берем,-1 дефолтное значение

    }
}
