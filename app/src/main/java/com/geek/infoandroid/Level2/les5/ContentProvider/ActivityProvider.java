package com.geek.infoandroid.Level2.les5.ContentProvider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
//разобраться,непонятная тема
//если нам нужно предоставвить доступ к данным нашего приложение , просто нужно класс наследовать от контент провайдера ,переопределить круд методы и через них венуть данные либо с бд либо с другого места хранених данных
public class ActivityProvider extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermission();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermission() {
        if(checkSelfPermission(Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){//если разрешение на чтение кантактов не получено , то...
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},100);//запросить разрешение на чтение контактов
        }else{//если разрешение уже дали
            ContentResolver contentResolver = getContentResolver();//получить контент ресловер через который будем доставать инфу
            Cursor cursor= contentResolver.query//делаем запрос на инфу по контактам(в курсор можно засунуть все или выбрать параметры по которым будем брать контакты)
                    (ContactsContract.Contacts.CONTENT_URI,//ресурс в который мы обращаемся за контактами
                            null,
                            null,//условие для выборки
                            null,
                            ContactsContract.Contacts.DISPLAY_NAME + "ASC");


            //после инициализации данных (когда все достали с контактов ,что нам необходимо работаем уже с ними
            cursor.getCount();//количество контактов
            //подвинули на позицию 0
            if(cursor.moveToPosition(0)){//если курсор передвинулся на контакт номер 0 и он есть, то
                String contact = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));//строчка для получения контакта
            }

        }
    }

    private void ribalkaContactsGet(){
        String previousName = "";
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        while (cursor != null && cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            if(!name.equals(previousName)){//если мы еще не использовали это имя
                previousName = name;/*ПРОСТО ЧТОБ НЕБЫЛО КРАСНЫМ
                Contacts contacts = new Contacts();//клаасс контейнер
                contacts.setName(name);//методы класса контейнера
                contacts.setPhone(ContactsContract.CommonDataKinds.Phone.NUMBER);
                data.add(contacts);//добавляем его в аррай лист*/
            }
        }
        initRV();

    }

    private void initRV() {
        //тут инициализируем наш список и добавляем туда наш список
    }
}
