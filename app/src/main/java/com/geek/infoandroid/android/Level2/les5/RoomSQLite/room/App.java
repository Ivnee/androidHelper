package com.geek.infoandroid.android.Level2.les5.RoomSQLite.room;

import android.app.Application;

import androidx.room.Room;
//ОБЯЗАТЕЛЬНО ДОБАВИТЬ В МАНИФЕСТ         android:name=".App"
public class App extends Application {
    private static App instance;//инизиализируем наш объект
    private EducationDataBase educationDataBase;//инициализируем нашу дб

    public static App getInstance(){//вернуть наш объект
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //сохраняем наш объект(для синглтона
        instance = this;

        educationDataBase = Room.databaseBuilder(getApplicationContext(),//создаем нашу бд ,даем контекст
                EducationDataBase.class,//даем класс нашей бд
                "education_database")//название нашей базы данных
                .allowMainThreadQueries()//метод для тестов ,позволяет все запросы делать в гуи потоке ,в реале так нельзя,надо будет создавать треды для запросов
                .build();//собрать
    }
    public EducationDao getEducationDao(){return educationDataBase.getEducationDao();}
}
