package com.geek.infoandroid.Level2.les5.RoomSQLite.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = TableStudent.class //указываем таблицу к которой привязываемся(указываем зависимость)
        ,parentColumns = "id"//указываем по каким столбцам связываем(id table student)
        ,childColumns = "student_id"//к айди студент
        ,onDelete = CASCADE))//и если удалим студента то и почта удалится этого студента
public class TableEmail {
    @PrimaryKey(autoGenerate = true)
    public long id;//наш идентификатор

    @ColumnInfo(name = "student_id")
    public long studentId;//айди наших студентов(может быть 2 маила)

    public String email;
}
