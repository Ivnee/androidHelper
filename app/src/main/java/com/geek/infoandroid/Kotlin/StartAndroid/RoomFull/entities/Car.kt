package com.example.testroom.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Employee::class,
        parentColumns = ["id"],//id Employee, оно должно соответствовать employee_id, если нет employee с id 4,то мы не сможем создать машину с id 4
        childColumns = ["employee_id"],//поле,в котором будет храниться айди сотрудника,к которому прикреплена эта машина
        onDelete = CASCADE//если удалим сотрудника,к которому прикрепрена эта машина,то удалится и машина
    )]
)
data class Car(//имя таблицы по умолчанию = car
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "model")//можем указать свое имя поля для дь
    val model: String,
    val year: Int,
    @ColumnInfo(name = "employee_id")
    val employeeId: Long
)