package com.geek.infoandroid.Level2.les5.RoomSQLite.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//КЛАСС ИНИЦИАЛИЗАЦИИ НАШЕЙ БД
@Database(entities = {TableStudent.class,TableEmail.class}//показываем какие таблицы мы будем создавать(ентити указывает какие таблицы нужно сгенерить)
                                , version = 1)//версия меняется если меняем стрктуру бд,создаем новые таблицы,новые столбцы и тд
public abstract class EducationDataBase extends RoomDatabase {//наследуемся от рума
    public abstract EducationDao getEducationDao();//метод который возвращаемт нам интерфейс со всеми нашими командами для бд
}
