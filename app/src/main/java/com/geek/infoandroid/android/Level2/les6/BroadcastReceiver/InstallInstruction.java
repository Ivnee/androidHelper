package com.geek.infoandroid.android.Level2.les6.BroadcastReceiver;

public class InstallInstruction {
/*asdfas

1)Создаем класс ресивер
                а)(MyReceiver extends BroadcastReceiver), наш класс ресивер должен наследовать бродкаст ресивер
                б)он заставит нас переопределить метод onReceive(context,intent) - этот метод вызывается когда случается событие на которое мы подписались(вкл\выкл авиарежим или любое наше событие ,которое мы описали)
                в)получаем наше сообщение intent.getStringExtra("КЛЮЧ_СООБЩЕНИЯ")
                г)ну и обрабатываем наше сообщение как захотим
2)подключаем ресивер в манифесте(это один из вариантов подключения)
        <receiver
            android:name=".MyReceiver123"
            android:enabled="true"//включить ресивер
            android:exported="true">//будем ли мы из вне получать сообщения
                <intent-filter>
                    <action android:name="ru.geekbrains.broadcastsender.message" />//экшн,путь по которому получаются сообщения от бродкаста(типа как в интенте,можно от разных приложений посылать события на этот путь)(т.е. какие сообщения мы слушаем)
                </intent-filter>
        </receiver>
2.1) подключаем ресивер по другому,через мэйн активити(програмно)!!!!!!!!!!ЛУЧШЕ РЕГЕСТРИРОВАТЬ ПРОГРАМНО , НЕКОТОРЫЕ НЕ ПОДКЛЮЧАТСЯ ЧЕРЕЗ МАНИФЕСТ!!!!!!!!!1
                    а)так же наследуем наш ресивер от BroadcastReceiver(AirplaneReceiver extends BroadcastReceiver)
                    б)создаем экземпляр ресивера в мэйн активити  AirplaneReceiver receiver = new Airplanereceiver()
                    в)registerReceiver(airplaneReceiver, new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));регистритуем ресивер програмным путем
                    в)подписали его на изменения авиарежима(можно указать свой какой-нибудь интент фильтр и посылать по этому пути какую-то инфу
                    г)unregisterReceiver(airplandeReceiver); в он дестрой включаем отмену регистрации(если это необходимо)




*/
}
