package com.geek.infoandroid;

public class ManifestCommands {
/*

    <uses-permission android:name="android.permission.INTERNET"/> ///дает доступ к интернету (подключаем при получении данных с интернета )
    <uses-permission android:name="android.permission.READ_CONTACTS"/>//достур к запросу на чтение контактов телефона

    <application
    android:configChanges="orientation|screenSize"> <!-- не создает новые  активити при повороте-->
    android:allowBackup="true"
    android:icon="@mipmap/ic_launcher"             //иконки нашего приложения(лежит в мипмап)
    android:label="@string/app_name"               //имя нашего приложения(лежит в строковых ресурсахString)
    android:roundIcon="@mipmap/ic_launcher_round"
    android:supportsRtl="true"                     //поддерживает написание текста справа налево
    android:theme="@style/AppTheme">               //ЗАДАЕТСЯ ТЕМА ПРИЛОЖЕНИЯ(ЦВЕТА ,ТУЛБАРЫ И ТД)
        <activity android:name=".Fragments5.ActivityForFragment" android:theme="@android:style/Theme.NoTitleBar"/>//задается тема конкретной активити
        <activity android:name=".MainActivity">
        android:configChanges="orientation|screenSize" //не пересоздает экран при повороте,нужно самому обрабатывать поворот экрана(не вызываются никакие жизненные циклы при повороте)
        android:screenOrientation="portrait">      .....Блокирует ориентацию(нельзя перевернуть экран)указывается в активити
            <intent-filter> стандартный фильтр для запуска //задает системные события на которые реагирует наша активити
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />//открывать данную активити при запуске апп
            </intent-filter>

            //фильтр для открытия нашего приложения через интент
            <intent-filter>//свой фильтр для запуска через интент
                <action android:name="android.intent.action.VIEW"/>//активити является вьюхой
                <category android:name="android.intent.category.DEFAULT"/>//дефолтная для запуска для отправляемого интента(в data указываем принимаемый интент)
                <category android:name="android.intent.category.BROWSABLE"/>//позволяет просматривать активити(для нее устанавливаем дата схему и дата хост) без нее не смоги бы написать дата
                <data android:scheme="ivan"//дата схема указывается в ури для интента
                    android:host="ognev"/>//дата хост тоже самое ,в том же ури
            </intent-filter>

            <activity android:name=".MainActivity">//добавить еще одну активити(интентфильтр не обязателен,если не нужен какой-то доп функционал(??))
        </activity>

        <service//прописываем сервис в манифесте
            android:name=".MyService"//указываем какой сервис мы подключаем
            android:enabled="true"
            android:exported="true"></service>

               <receiver
            android:name=".MyReceiver123"
            android:enabled="true"//включить ресивер
            android:exported="true">//будем ли мы из вне получать сообщения(вне нашего приложения)
                <intent-filter>
                    <action android:name="ru.geekbrains.broadcastsender.message" />//экшн,путь по которому получаются сообщения от бродкаста(типа как в интенте,можно от разных приложений посылать события на этот путь)(т.е. какие сообщения мы слушаем)
                </intent-filter>
        </receiver>
    </application>
*/
}
