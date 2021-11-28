package com.geek.infoandroid.android.Fragments5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.geek.infoandroid.R;

public class Fragment2 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment2,container,false);//определяет фрагмент ,аттач - жесткое прикрепление к активити-false

    }
    private static Fragment2 createThisFragment(BusData classContainer){
        Bundle bundle = new Bundle();//создаем бандл для хранения данных
        //bundle.putSerializable(classContainer);//вставляем данные в бандл,должно быть серриалайзабл
        Fragment2 fragment2 = new Fragment2();//создаем наш фрагмент
        fragment2.setArguments(bundle);//сохраняем бандл с переданными данными
        return fragment2;
    }
}
