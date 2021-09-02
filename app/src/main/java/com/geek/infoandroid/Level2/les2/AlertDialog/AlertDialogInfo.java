package com.geek.infoandroid.Level2.les2.AlertDialog;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.geek.infoandroid.R;

public class AlertDialogInfo extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        button = findViewById(R.id.button1);
        final String[] items = getResources().getStringArray(R.array.array);//взять массив стрингов из ресурсов
        final boolean[] choosen = {true,false,true};// массив булинов для того чтоб показать какие элементы выбраны и не выбраны по умолчанию
        final View createDialogViews = getLayoutInflater().inflate(R.layout.activity_main_main,null);//раздуваем из макета нужного нам и потом ставим в алерт диалог то что мы сами создали в макете

        button.setOnClickListener(new View.OnClickListener() {//установить создание альтер диалога на кнопку
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialogInfo.this);//создаем алерт диалог и его билдер
                builder.setTitle("установить название")
                        .setMessage("установить сообщение")
                        .setIcon(R.mipmap.ic_launcher_round)//установить картинку
                        .setCancelable(false)//нельзя выйти на кнопку бэк или нажав мимо диалога
//создаем в ручную наш диалог из лейаута ( можно эдит текст добавить и кнопки и тд)
                        .setView(createDialogViews)//сетим самостоятельно собранный макет (можно добавить к ней тайтл и позитив батн(для сложного макета лучше использовать фрагмент диалог
//массивы
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {//добавить список среди которых кликаешь на что-то одно и делается код
                                System.out.println(items[i]);//распечатать элемент(i это index элемента)
                            }
                        })
                        .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {//(массив,выбранный по умолчанию элемент,листенер) эта функция вставляет массив в котором ты галочкой выбираешь нужный элемент и нажимаешь либо да либо нет(да и нет устанавливаются самостоятельно ,сет позитив и негатив батн)-1=никакой элемен не выюран
                                String msg = items[i];//i это айди итема ,сохраняем сообщение и в дальнейшем обрабатываем где нам нужно(когда мы выбрали в диалоге какой-то пункт,тогда срабатывает метод он клик
                            }
                        })
                        .setMultiChoiceItems(items, choosen, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {//(массив стрингов, какие выбраны элементы,листенер)здесь элементы выбираются галочкой ,можно выбрать сразу несколько
                                choosen[i] = b;//i - index , b - выбрано или нет,получаем массив с тру и фолсами который в дальнейшем сможем применять если выбрано это и это то делаем такой - то код
                            }
                        })
//кнопки устанавливаем
                        .setNeutralButton("незнаю", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                System.out.println("выполняем код в случае если нажали кнопку незнаю");
                            }
                        })
                        .setNegativeButton("нет", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                System.out.println("выполняем код при нажатии на нет");
                            }
                        })
                        .setPositiveButton("да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                System.out.println("выполняем код ,при нажатии на позитив батн(да)");
                            }
                        }).create().show();//можно и сразу запустить,без создания алерта,просто шоу
                AlertDialog alert = builder.create();//собираем наш алерт диалог
                alert.show();//показываем
                alert.dismiss();//закрыть окно(можно установить на какой-нибудь действие,но при нажатии на кнопки окно и так закроется)

            }
        });
    }
}
