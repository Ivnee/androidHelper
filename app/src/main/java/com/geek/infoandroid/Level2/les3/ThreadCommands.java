package com.geek.infoandroid.Level2.les3;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThreadCommands extends AppCompatActivity {
    TextView textView;
/*
1)runOnUiThread() в фрагменте getActivity.runOnUiThread()
2)textView.post()//запустить через вью в gui потоке
3)а)handler.post() в потоке в котором был создан хендлер
  б)handler.postDelayed()//запустить через какой-то время (обычно для тестов или каак костыль используют)в финальном коде не очень
*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HandlerThread handlerThread = new HandlerThread("name");//создаем хендлер тред для неумирающего потока
        handlerThread.start();//стартуем его, после нужно засетить в хендлер//код выполняется так же через post
        final Handler handler2 = new Handler(handlerThread.getLooper());//создали неумирающий поток,можно запустить и не тратится время на создание(указали какой поток будет запускать хендлер через пост,это будет тот поток ,который мы передали в качестве аргумента)

        //пример из власкина,как работает хендлер тред гет лупер(бессмертный поток)
        ////////////////////////////////////////////////////////////////////////////////
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler2.post(new Runnable() {//поток лупер(не подыхает после выполнения задачи),пока не выполнит одну задачу, не сможет выполнить
                    // вторую(тут все в очередь встает,если нужно делать 2 задачи одновременно и не тратить время на создание потока ,то делаем 2 потока лупера)

                    @Override
                    public void run() {
                        //проводим рассчеты какие - то и получаем результат
                        textView.post(new Runnable() {//можно сетить в основной поток еще через хендлер ,созданный в UI потоке
                            @Override
                            public void run() {//вызываем пост у текст вью
                                //вставляем результаты вычислений в основной поток ,например сет текст в какой-нибудь вью или еще что-то
                            }
                        });
                    }
                });
            }
        });
        //////////////////////////////////////////////////////////////////////////////

//когда нужно убить вечный поток вызываем
        handlerThread.quit();//убить поток
        handlerThread.quitSafely();//убить поток после того как завершится выполняемая им операция


        final Handler handler = new Handler();//хендлер запоминает поток в котором был создан(или в качестве аргумента может принмать поток ,в котором он будет работать
        new Thread(new Runnable() {
            @Override
            public void run() {
                //final выполняем сложнй код ,расчеты
                runOnUiThread(new Runnable() {//вызывает основной поток
                    @Override
                    public void run() {//более приорететно по словам препода
                        //запостить результат кода в основной поток
                    }
                });
                textView.post(new Runnable() {
                    @Override
                    public void run() {//этот способ тоже сетит в основной поток ,потому как все вьюхи это основной поток
                        textView.setText("сетим результат");
                    }
                });
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //делаем код в потоке в котором был создан хендлер
                    }
                });
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //код
                    }
                }, 500);//и через какое время его запустить

            }
        }).start();
    }
}
