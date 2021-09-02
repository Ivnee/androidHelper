package com.geek.infoandroid.Level2.les5.RoomSQLite.room;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {TableStudent.studentName})})//указали что это наша таблица с помощью аннатации ентити(указали поле по которому можно осуществлять быстрый поиск)
public class TableStudent {
    public final static String studentName="myName";
    @PrimaryKey(autoGenerate = true)//ключить автоинкремент для этого поля
    public long id;

    @ColumnInfo(name = studentName)//создаем колонку в нашей таблице с именем myName(если не указывать аннатацию , то колонка создастся автоматически по имени поля)
    public String myName;//колонка будет называться name если не указать колумн инфо

    //для удобного разделения таблицы по категориям(по сути будет одна таблица,просто чтоб легче было искать поля)
    @Embedded//этой аннотацией вставляем поля таблицы адресс в таблицу студент(по виду в базе будет как одна таблица где много столбиков а не одна в другой)
    public TableAddAdress tableAddAdress;
}
