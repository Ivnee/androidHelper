package com.geek.infoandroid.Level1.les2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.geek.infoandroid.databinding.ActivityRecyclerBinding;

public class FragBinding extends Fragment {
    ActivityRecyclerBinding bind;//ActivityRecycler это имя хмл а Binding    библиотека(так выбираем наш хмл)

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = ActivityRecyclerBinding.inflate(inflater,container,false);//раздуваем наш биндинг
        View view = bind.getRoot();//раздуваем наш лэйаут
        return view;//возвращаем
    }
}
