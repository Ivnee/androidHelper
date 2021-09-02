package com.geek.lection1.Fragments5;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentLifeCycle extends Fragment {
    //кратко
/*
    onAttach //получаем ссылку на активити ,вызывается при присоединении фрагмента к активити
    onCreate
    onCreateView//метод в котором мы подключаем лейаут к нашему фрагменту и получаем все данные(возвращает view (inflater.inflate))
    onViewCreate//метод для инициализации вьюх фрагмента
    onActivityCreate
    onStart
    onResume
    onPause
    onStop
    onDestroyView
    onDetach
*/

    @Override//получаем ссылку на активити ,вызывается при присоединении фрагмента к активити
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Override//аналог активити, создает
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override//метод в котором мы подключаем лейаут к нашему фрагменту и получаем все данные(возвращает view (inflater.inflate))
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override//метод для инициализации вьюх фрагмента
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override//аналон
    public void onStart() {
        super.onStart();
    }

    @Override//аналог
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override//уничтожает наш фрагмент
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override//вызывается когда фрагмент отсоединен от своей активити
    public void onDetach() {
        super.onDetach();
    }
}
