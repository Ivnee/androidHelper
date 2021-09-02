package com.geek.infoandroid.Level2.les5.RoomSQLite.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao//помечаем класс ,указывает на то что он работает с данными таблицы
public interface EducationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)//указываем аннотацией в таблицу вставить студента( реплейс значит если такой уже существует,заменить)
    long insertStudent(TableStudent tableStudent);//лонг- вернет айдишник строки , в которую вставили студента

    @Update//изменить что-то в студенте или обновить его
    void updateStudent(TableStudent tableStudent);

    @Delete//удаление
    void deleteStudent(TableStudent tableStudent);//удалить какого-то студента
//Query запросы
    @Query("DELETE FROM TableStudent WHERE id = :id")//конкретные запросы(удалить студента по айди, (:) указывает на то что берем из нашего аргумента в методе
    void deleteStudentById(long id);

    @Query("SELECT * FROM TableStudent WHERE id = :id")//выбрать студента по конкретному айди
    TableStudent selectStudentByID(long id);

    @Query("SELECT * FROM TableStudent")//получить всех студентов
    List<TableStudent>getAllStudents();

    @Query("SELECT COUNT() FROM TableStudent")//получить количество студентов в таблице(колво строк)
    long getCountStudents();

    @Query("SELECT * FROM TableEmail WHERE student_id = :studentId")//получаем почтовые ящики или 1 ящик студента по айди
    List<TableEmail> getEmailByStudent(long studentId);


    // Запрос сразу из двух таблиц(надо разобраться ,какой-то мутный)
/*
    @Query("SELECT myName, id " +
            "FROM student " +
            "INNER JOIN email ON student.id = email.student_id")// JOIN - связать данне таблицы и укащываем с какой таблицы
    List<StudentWithEmail> getStudentWithEmail();
*/




    /*ЕЩЕ КОМАНДЫ , РАЗОБРАТЬСЯ
    *     // Запрос через Relation
    @Query("SELECT * FROM student")
    List<StudentEmail> getStudentEmails();

    // Получить через Relation одного студента
    @Query("SELECT * FROM student WHERE id = :id")
    StudentEmail getOneStudentEmails(long id);

    @Insert
    long insertEmail(Email email);

    @Query("SELECT id, first_name, last_name FROM student")
    Cursor getStudentCursor();

    @Query("SELECT id, first_name, last_name FROM student WHERE id = :id")
    Cursor getStudentCursor(long id);*/







}
