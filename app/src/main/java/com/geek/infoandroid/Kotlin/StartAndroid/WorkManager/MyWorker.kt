package com.example.testworkmanager

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

//ВЫПОЛНЯЕТСЯ В ОТДЕЛЬНОМ ПОТОКЕ
class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
//получаем и передаем данные обратно
        val str = inputData.getString("KeyA")//забираем данные,которые
        val outputData1 = Data.Builder()// ворк возвращает какие-то данные
            .putString("KeyC","backString")//эти данные пойдут в следующую задачу (воркер)beginWith(worker).then(worker2)перейдут данные в воркер 2
            .build()
        this.

        applicationContext//чтоб получить контекст используем этот метод

        Result.failure(outputData1)//задача не выполнилась(передаем выходные данные)
        Result.success(outputData1)//задача завершилась успешно(передаем данные обратно)
        return Result.retry()//если вернуть ретрай,задача повторится
    }
}
