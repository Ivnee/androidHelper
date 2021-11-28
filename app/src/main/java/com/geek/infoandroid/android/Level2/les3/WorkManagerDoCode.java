package com.geek.infoandroid.android.Level2.les3;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WorkManagerDoCode extends Worker {
    public WorkManagerDoCode(@NonNull Context context, @NonNull WorkerParameters workerParams) {//просто конструктор,требует
        // (Можно здесь инициализировать какие-нибудь даннные)
        super(context, workerParams);
    }//наследуемся от класса воркер
    @NonNull
    @Override
    public Result doWork() {//выполняем здесь код и возвращаем результат
        System.out.println("start");
        System.out.println("finish");
        return Result.success();//делает работу и возвращает результат или вернуть failure
    }
}
