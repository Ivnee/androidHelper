package com.geek.infoandroid.Kotlin.les6ServiceAndBroadcast

import android.content.Intent
import androidx.core.app.JobIntentService
import androidx.localbroadcastmanager.content.LocalBroadcastManager

//backgroundService
//СЕРВИСЫ НУЖНО ОБЪЯВЛЯТЬ В МАНИФЕСТЕ
class CatchService : JobIntentService() {
    // если IntentService() запускается startService(),(выполняется в отдельном потоке)
    //если нужно JobIntentService ,то нужно запускать через JobIntentService.enqueueWork(context,класс,джоб айди,и интент)(выполняется в отдельном потоке)
    //если мы делаем обычный Service  то он выполняется в основном потоке


    //интент сервис уже не актуален
    companion object {
        const val ARG_CITY = "ARG_CITY"
        const val BROADCAST_ACTION = "ACTION_RESULT"
        const val ARG_CITY_NAME = "CITY"
    }

    override fun onCreate() {
        super.onCreate()
        //(applicationContext as App).appComponent.inject(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val city = intent?.getStringExtra(ARG_CITY)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onHandleWork(intent: Intent) {//Для интент сервиса
        val city = intent.getStringExtra(ARG_CITY)
        val safeCity = city ?: return

//BROADCAST RECEIVER
        //Создаем броадкастРесивер
        val intentt = Intent(BROADCAST_ACTION).apply {
            putExtra(ARG_CITY, "dmitrov")
        }.also {
            sendBroadcast(it)//отправить всем этот интент(отловим в фрагменте)

            LocalBroadcastManager.getInstance(this)
                .sendBroadcast(it)//отправить только внутри приложения
        }


    }

}