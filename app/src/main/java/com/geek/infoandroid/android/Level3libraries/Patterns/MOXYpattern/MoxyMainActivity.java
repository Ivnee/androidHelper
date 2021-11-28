package com.geek.infoandroid.android.Level3libraries.Patterns.MOXYpattern;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.geek.infoandroid.android.Level3libraries.Patterns.MVPpattern.MvpMainActivity;
import com.geek.infoandroid.R;
/*
      implementation 'tech.schoolhelper:moxy-x:1.7.0' //подключаем moxy
      implementation 'tech.schoolhelper:moxy-x-androidx:1.7.0'
      annotationProcessor 'tech.schoolhelper:moxy-x-compiler:1.7.0'
*/
//еще есть мосби(фигня видимо),тоже самое ,только не сохраняет состояние при повороте, только презентер сохраняет(нет вью стейта)
//ГЛАВНЫЙ ПЛЮС МОКСИ!!при пересоздании нашего экрана,все данные сохраняются(@InjectPresenter сохраняет наш презентор с его данными)и еще
// есть "viewState()"-она сохраняет последнее состояние нашей вьюхи,т.е. когда мы вызываем через setViewState()методы,он сохраняет это в очередь
// и при пересоздаии достает последнее состояние вьюхи,которое было засечено
public class MoxyMainActivity extends MvpMainActivity//для мокси нужно наследоваться от MvpMainActivity- Обертка над AppCompatActivity
implements MoxyExampleView, View.OnClickListener{//имплементим вью,чтоб активити числилась как вьюха
    @InjectPresenter//аннотация для того чтобы указать наш презентор(когда активити создастся этот презентор заинджектится)
    Presenter presenter;//класс презентор,который мы создали(для того чтоб он заинжектился ,нужно попмететь его класс @InjectViewState)

    private Button btnCounter1;
    private Button btnCounter2;
    private Button btnCounter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnCounter1 = findViewById(R.id.moxy_btn1);
        btnCounter2 = findViewById(R.id.moxy_btn2);
        btnCounter3 = findViewById(R.id.moxy_btn3);
        btnCounter1.setOnClickListener(this);
        btnCounter2.setOnClickListener(this);
        btnCounter3.setOnClickListener(this);
    }

    public void setTTT(){//просто метод для проверки вызова с помощью getViewState()
    }
    @Override
    public void onClick(View v) {
        presenter.buttonClick(v.getId());
    }


    @Override
    public void setButtonText(int btnIndex, int value) {
        switch(btnIndex){
            case 1:
                btnCounter1.setText("Количество = " + value);
                break;
            case 2:
                btnCounter1.setText("Количество = " + value);
                break;
            case 3:
                btnCounter1.setText("Количество = " + value);
                break;
        }

    }
}
