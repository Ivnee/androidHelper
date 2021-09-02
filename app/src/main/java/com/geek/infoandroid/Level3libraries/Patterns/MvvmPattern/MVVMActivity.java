package com.geek.infoandroid.Level3libraries.Patterns.MvvmPattern;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.geek.infoandroid.R;

public class MVVMActivity extends AppCompatActivity implements View.OnClickListener {
//    implementation 'androidx.lifecycle:lifecycle-viewmodel:2.2.0-alpha02'// подключаем lifeData !
//    implementation 'androidx.lifecycle:lifecycle-extensions:2.0.0'
//    annotationProcessor 'androidx.lifecycle:lifecycle-compiler:2.2.0-alpha02'
    private Button btnCounter1;
    private Button btnCounter2;
    private Button btnCounter3;

    MVVMViewModel viewModel;//объявляем вью модель


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moxy_activity);
        btnCounter1 = findViewById(R.id.moxy_btn1);
        btnCounter2 = findViewById(R.id.moxy_btn2);
        btnCounter3 = findViewById(R.id.moxy_btn3);
        btnCounter1.setOnClickListener(this);
        btnCounter2.setOnClickListener(this);
        btnCounter3.setOnClickListener(this);
//получаем нашу вью модель с лайвДатой для событий
        //такая запись работает когда у нас есть конструктор по умолчанию???2:03
        viewModel = ViewModelProviders.of(this).get(MVVMViewModel.class);//подписать наш класс (активити) к MVVMViewModel
//при пересоздании экрана, последнее значение которое было в нашей лайф дате (observe) мы его получим
        viewModel.seconds.observe(this, new Observer<Integer>() {//сделали автомотическую отписку и подписку на (seconds) в вью модели(если не будем отписываться,то при пересоздании экрана все рухнет и обнулится)
            @Override
            public void onChanged(Integer data) {
                btnCounter1.setText("Количество = " + data);
            }
        });
        viewModel.minutes.observe(this, new Observer<Integer>() {//подписка на liveData(на наш обсервабл)(вью модел)
            @Override
            public void onChanged(Integer integer) {
                btnCounter2.setText("Количество = "+ integer);
            }
        });
        viewModel.hours.observe(this, new Observer<Integer>() {//мы подписались на Hours, и когда его значение изменится,оно прийдет нам в этом методе обсерв в виде Integer.Integer, потому что мы указали его в  ViewModel при создании MutableLiveData
            @Override
            public void onChanged(Integer integer) {
                btnCounter3.setText("Количество = " + integer);
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.moxy_btn1:
                viewModel.incSec();//у нашей вью модели вызываем метод увеличить секунду
                break;
            case R.id.moxy_btn2:
                viewModel.incMin();
            case R.id.moxy_btn3:
                viewModel.incHours();
        }
    }
}
