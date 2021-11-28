package com.example.testroom.entities

import android.location.Address
import androidx.room.*
import androidx.room.ColumnInfo.TEXT

//класс будет использоваться для создания таблицы в бд
//@TypeConverters(EmployeeConvertor::class)//конвертор можно указать и для всего ентити
@Entity(tableName = "tableName")//имя таблицы,по умолчанию имя-название класса
data class Employee(
    @PrimaryKey(autoGenerate = true)//коюч таблицы,генерируется автоматически
    val id:Long,
    @ColumnInfo(name = "name",typeAffinity =TEXT)//свое имя поля для таблицы//можем указать тип данных
    val firstName:String,
    val lastName:String,
    val salary:Int,
    @Embedded//подскажет что нужно взять поля из Address и считать их своими полями(тоесть в базе будет таблица со всеми полями),а в коде нужно вставить экземпляр Address в Employee
    val address: Address,
    @Ignore//это значение не будет записываться в таблицу
    val ignoreVal:String,
    @ColumnInfo(name = "department_id")//сохраняет айди департамента,в котором работает сотрудник
    val departmentId:Int,
    @TypeConverters(HobbiesConvertor::class)//указали как конвертировать данные в бд и как из нее доставать
    val hobbies:List<String>,
    val birthday:Long
    )