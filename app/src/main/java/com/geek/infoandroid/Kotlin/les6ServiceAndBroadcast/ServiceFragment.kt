package com.geek.infoandroid.Kotlin.les6ServiceAndBroadcast

import android.content.*
import android.content.Context.BIND_AUTO_CREATE
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.geek.infoandroid.R
//СЕРВИСЫ НУЖНО ОБЪЯВЛЯТЬ В МАНИФЕСТЕ
class ServiceFragment : Fragment(R.layout.fragment1) {

    //для Bind service
    var binder: FibonacciService.ZelBinder? = null
    private var isBound = false


    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            isBound = true
            binder = (service as FibonacciService.ZelBinder)
            //НЕ ЗАБЫТЬ ОТПИСАТЬСЯ В МЕТОДЕ ОН СТОП
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            binder = null
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn).setOnClickListener {
            if (isBound) {
                print(binder?.nextNumber())//если привязан то выводим число
            } else {
                val intent = Intent(requireContext(), FibonacciService::class.java)
                //(интент,сервис коннекшн создали выше,константа из андроида)
                requireActivity().bindService(intent,serviceConnection,BIND_AUTO_CREATE)//у активити есть метод байд сервис(у любой)

            }
        }

        //ForegroundService
        view.findViewById<Button>(R.id.btn2).setOnClickListener{
            val intent = Intent(requireContext(),ForegroundService::class.java)
            ContextCompat.startForegroundService(requireContext(),intent)//foreground запускается через ContextCompat
        }


        //backgroundService
        requireContext().startService(
            Intent(
                requireContext(),
                CatchService::class.java
            ).apply {
                putExtra(CatchService.ARG_CITY_NAME, "Dmitrov")
            })//запустили сервис в который передали аргумент

    }

    //БРОАДКАСТ РЕСИВЕР
    override fun onStart() {
        super.onStart()
        IntentFilter(CatchService.BROADCAST_ACTION).also {
            LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
                weatherCityReceiver,
                it
            )//локальный (внутри приложеняя)     ресивер
            requireActivity().registerReceiver(
                weatherCityReceiver,
                it
            )//регистрируем ресивер с этим интент фильтром
        }
    }

    override fun onStop() {
        super.onStop()
        LocalBroadcastManager.getInstance(requireContext())
            .unregisterReceiver(weatherCityReceiver)//отрегистрировать локальный бродкаст
        requireActivity().unregisterReceiver(weatherCityReceiver)//снимаем регистрацию ресивера,чтоб избежать утечки ресурсов
        if(isBound){
            requireActivity().unbindService(serviceConnection)
        }
    }

    private val weatherCityReceiver = object : BroadcastReceiver() {
        //ловим броадкаст ,который послали из сервиса и достаем из интента данные
        override fun onReceive(
            context: Context?,
            intent: Intent?
        ) {//так же нужно подписываться и отписываться от ресивера
            //либо в он старт и он стоп либо в он креейт и он дестрой
            print(intent?.getStringExtra(CatchService.ARG_CITY_NAME))//вставили в текст вью данные
        }

    }
}