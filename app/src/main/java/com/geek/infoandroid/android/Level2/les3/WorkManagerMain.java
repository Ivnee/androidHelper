package com.geek.infoandroid.android.Level2.les3;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class WorkManagerMain extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //вставляем все это в listener на какую-то вьюху(сначала нужен будет подключатель в гредле)(хотя где угодно,не только листенер)
        //событие ,после которого будем выполнять
        Constraints constraints = new Constraints.Builder()//одну из этих условий мы вставим в setConstraints(constraints)в оне тайм ворк реквест
                .setRequiresBatteryNotLow(true)//выплдняем како-йто событие после того как батарея подзарядилась и много(власкин .. не низкий заряд батареи)
                .setRequiredNetworkType(NetworkType.CONNECTED)//когда приоединились к интернету ,выполняем(власкин сказал запускает работу только когда подключен вайфай)
                .setRequiresCharging(true)//когда вставлен в зарядку , то выполняй эту работу
                .build();

        //выполнить воркер 1 раз
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest//создаем запрос на выполнение
                .Builder(WorkManagerDoCode.class)//указываем класс, который будем выполнять(он должен наследоваться от класса Worker)
                .setInitialDelay(5, TimeUnit.SECONDS)//после какого события будем выпоолнять(спустя 5 секунд после нажатия) ИЛИ!!!!!--->
                .setConstraints(constraints)//после того как произошло событие ,которое мы установили
                .build();//собрать
        //либо можно многоразово выполнять задачу через :
        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(WorkManagerDoCode.class)//но он не четко выполняет,если поставить выполнять каждый час,может выполнить через 1:01 например,как освободится в общем
                .setConstraints(constraints)
                .build();
        WorkManager workManager = WorkManager.getInstance();//получаем ворк менеджер
        workManager.enqueue(workRequest);//кладем задачу в очередь выполнения

    }
}
