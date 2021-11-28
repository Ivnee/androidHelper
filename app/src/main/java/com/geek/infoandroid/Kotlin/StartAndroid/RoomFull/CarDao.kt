package com.example.testroom

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.testroom.entities.Car
import com.example.testroom.entities.Employee
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single


@Dao
interface CarDao {
    @Query("SELECT * FROM car")
    fun getAll():List<Car>
    @Insert
    fun insert(car: Car)
    @Delete
    fun delete(car: Car)

    @Query("SELECT * FROM car")
    fun getAllFlowable(): Flowable<List<Employee>>
    @Query("SELECT * FROM car WHERE id = :id")
    fun getFlowableById(id:Long):Flowable<Employee>//получаем данные в flowable

    @Query("SELECT * FROM car WHERE id = :id")
    fun getSingleById(id:Long): Single<Employee>//Single это флоубл в котором приходит только одно ,либо onNext либо onError

    @Query("SELECT * FROM car WHERE id=:id")
    fun getMaybeById(id:Long):Maybe<Employee>

}