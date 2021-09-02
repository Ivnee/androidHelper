package com.geek.infoandroid.Level1.les6;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geek.infoandroid.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private List<DataClass> adapterData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        RecyclerView rv = findViewById(R.id.rv);

        adapterData = new ArrayList<>(Arrays.asList(new DataClass(12,"asd"),
                new DataClass(12,"zel"),
                new DataClass(12,"volg"),
                new DataClass(12,"dmtr"),
                new DataClass(12,"msc")));
        rv.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        RvAdapter adapter = new RvAdapter();
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter.setDataList(adapterData);
        adapter.setOnMyClickListener(new RvAdapter.MyOnClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getApplicationContext(),"position "+ position,Toast.LENGTH_SHORT).show();
            }
        });
        rv.setAdapter(adapter);

    }
}