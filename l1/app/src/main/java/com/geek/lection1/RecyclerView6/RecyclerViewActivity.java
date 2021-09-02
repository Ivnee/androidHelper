package com.geek.lection1.RecyclerView6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.geek.lection1.R;

import java.util.ArrayList;
import java.util.Arrays;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList <String > ar = new ArrayList<>(Arrays.asList("a","b","c","d"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = findViewById(R.id.recyclerView);
        setupRV();//устанавливаем RecyclerView
    }

    private void setupRV() {
        //создаем лейаут для ресайкл вью(как будет распологаться список)
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());//горизонтально или вертикально(в обратном или прямом порядке)
        RecyclerAdapter adapter = new RecyclerAdapter(ar);//создаем экземпляр нашего адаптера,вставляем в него список данных

        recyclerView.setLayoutManager(layoutManager);//устанавливаем в список нужный лейаут
        recyclerView.setAdapter(adapter);//устанавливаем в список экземпляр адаптера с нашими данными
        
    }
}
