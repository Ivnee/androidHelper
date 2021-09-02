package com.geek.infoandroid.RecyclerView6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.geek.infoandroid.R;

import java.util.ArrayList;
import java.util.Arrays;

public class RecyclerActivity extends AppCompatActivity implements ForClickOnRVitem {
    private RecyclerView recyclerView;//находим ресайкл вью
    private ArrayList<String> spisokDannihArray= new ArrayList<>(Arrays.asList("1","2","3","4","5"));//лист для списка ресайкл вью

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView = findViewById(R.id.recycler_view);
        setupRV();
    }

    private void setupRV() {
        //делаем разделитель для элементов (декоратор)
        DividerItemDecoration did = new DividerItemDecoration(getBaseContext(),LinearLayoutManager.VERTICAL);//создаем декоратор и устанавливаем вертикальную ориентацию
        did.setDrawable(ContextCompat.getDrawable(getBaseContext(),R.drawable.decorator_item));//устанавливаем наш декоратор
        //и потом просто сетим декоратор в ресайклер вью(смотреть ниже)
        //1) контекст 2) ориентация 3) перевернуть?
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.HORIZONTAL,false);//создаем менеджер ,который будет выставлять элементы рв
        RecyclerAdapter adapter = new RecyclerAdapter(spisokDannihArray,this);//создаем экземпляр адаптера который сами составляли и подаем на вход список элементов и наш класс для вызова метода коллбэка
        recyclerView.setAdapter(adapter);//назначаем адаптер
        recyclerView.addItemDecoration(did);//добавляем аазделитель между каждой строкой списка рв
        recyclerView.setLayoutManager(linearLayoutManager);//назначаем лейаут менеджер

    }

    @Override
    public void onRVitemClick(String itemText) {//переопределяем метод для использования в ресайкл вью
        System.out.println("то что мы делаем в методе из ресайкл вью" + itemText);
    }
}