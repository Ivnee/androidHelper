package com.geek.infoandroid.Level2.les5.RoomSQLite.room;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//ОБЯЗАТЕЛЬНО ДОБАВИТЬ  КЛАСС Арр В МАНИФЕСТ         android:name=".App"
public class ActivityDB extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1:30 посмотреть как делается прослойка для работы с методамы базы данные(запихнуть методы дао в методы другого класса)
        //и вообще потом сделать по уроку прям таблицу,там много фишек с меню и ресайклом вью, как это все вместе работает и тд
        EducationDao educationDao = App.getInstance().getEducationDao();//сделали наш объект по запросам
        //методы нашей базы которые мы определили
        educationDao.deleteStudent(new TableStudent());
        educationDao.getAllStudents();
        educationDao.deleteStudentById(5);
    }
}
