package com.example.testroom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction
import com.example.testroom.entities.Car
import com.example.testroom.entities.Employee

@Dao
abstract class CarEmployeeDao {
    @Insert
    abstract fun insertEmployee(employee: Employee)
    @Insert
    abstract fun insertCar(car:Car)
    @Transaction//когда нужно сделать сразу несколько операций в одном методе( должно быть в абстрактном классе)
    fun insertEmployeeAndCar(car: Car,employee: Employee){
        insertEmployee(employee)
        insertCar(car)
    }

}