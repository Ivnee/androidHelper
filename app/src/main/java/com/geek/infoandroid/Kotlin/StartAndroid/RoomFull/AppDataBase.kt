package com.example.testroom

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.testroom.entities.Car
import com.example.testroom.entities.Employee

@Database(entities = [Employee::class, Car::class],version = 1)
abstract class AppDataBase: RoomDatabase() {
    companion object{
        val MIGRATION_1_2 = object :Migration(1,2){//в конструкторе указали старую и новую версии
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE tableName ADD COLUMN birthday INTEGER DEFAULT 0 NOT NULL")//указали что в таблицу добавили колонку бесдей с типом числа
            }
        }
    }
    abstract fun employeeDao():EmployeeDao
    abstract fun carDao():CarDao
    abstract fun departmentDao():DepartmentDao

}