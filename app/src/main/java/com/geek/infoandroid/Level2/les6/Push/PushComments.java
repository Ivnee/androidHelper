package com.geek.infoandroid.Level2.les6.Push;

public class PushComments {
    //ЧТО ЭТО ТАКОЕ ???
    //коггда к вам на смартфон приходит нотификация от какого-нибудь интернет-сервиса(не обычная нотификация ,созданная програмно внутри приложения)
    //способ 1) заходим в Tools- FireBase и там далее разобраться,в лекции сказали просто next next и все само соберется
    //способ 2)
    /*
    1)заходим на сайт https://console.firebase.google.com/ и регаемся,если нет акка
    2)заходим в firebase консоль и нажимаем добавить проэкт(указываем его наименование(любое))
    3)добавляем наше приложение
            а) выбираем андроид и открывается окно зарегестрируйте приложение
            б)в первом поле указываем наш пакет(это то что в гредл нижнем, applicationId "com.geek.infoandroid") и можем указать псевдоним , хэш ,но можно и не указывать и нажимаем далее
            в)далее скачивавем файл google-servise.json(вылезет сама кнопка скачать)и кладем этот файл в папку app нашего проэкта и нажимает далее
            г)добавляем в гредле зависимости
                г1)вставляем в гредл проэкта(верхний) в депенденсис строчку classpath 'com.google.gms:google-services:4.3.2'
                г2)в гредл модуль(нижний) додбавляем apply plugin: 'com.google.gms.google-services' , в самом верху ставятся плагины обычно
                г3)в гредл модуль в зависимости добавляем implementation 'com.google.firebase:firebase-messaging:20.0.0'
                                                        implementation platform('com.google.firebase:firebase-bom:26.7.0')
                г4) и на саите нажимаем кнопку синхранизировать
           д) добавляем в манифест <service android:name="com.geek.infoandroid.Level2.les6.Push.MyFirebase">
                                        <intent-filter>
                                            <action android:name="com.google.firebase.MESSAGING_EVENT" />
                                        </intent-filter>
                                    </service>
           е)и все,нажимаем обратно перейти в консоль
    4)далее заходим во вкладку слева - cloud Messaging и создаем наше сообщение:
            а)нажимаем send first message (и там можно все полня заполнить ,можно только текст )



 !!!!!   5) Для того чтоб получить это сообщение ,нужно создать свой класс MyFirebase который наследуем от FirebaseMessagingService и переопределяем его методы
            5а)в методе (переопределенном)onMessageReceived(RemoteMessage msg) мы получаем вью нужную инфу и обрабатываем ее
                    а)String title = msg.getNotification().getTitle();//титул уведомления
                    б)String text = msg.getNotification().getBody();//сам текст уведомления
                    в)получили всю инфу ,которую отправляли с сайта фаирбейс и обрабатываем ее в этом же методе с помощью NotificationCompat:
                                в1)   NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "2")
                                        .setSmallIcon(R.mipmap.ic_launcher)
                                        .setContentTitle(title)
                                        .setContentText(text);
                                NotificationManager notificationManager =
                                        (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
                                notificationManager.notify(messageId++, builder.build());
            5б)в переопределенном методе onNewToken(String наш_айди) получаем наш айдишник,нужно его обработать(отправлять на сервер,чтоб он мог обновлять ваше приложение)
//(глянуть лекцию можно про токены
                   а)либо поолучаем наш токен вручную, в любом месте где нам удобно :                   final EditText textToken = findViewById(R.id.textToken);
                                                                                                        FirebaseInstanceId.getInstance().getInstanceId()
                                                                                                                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                                                                                                    @Override
                                                                                                                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                                                                                        if (!task.isSuccessful()) {
                                                                                                                            Log.w("PushMessage", "getInstanceId failed", task.getException());
                                                                                                                            return;
                                                                                                                        }

                                                                                                                        // Get new Instance ID token
                                                                                                                        String token = task.getResult().getToken();
                                                                                                                        textToken.setText(token);
                                                                                                                    }
                                                                                                                });


    */

}
