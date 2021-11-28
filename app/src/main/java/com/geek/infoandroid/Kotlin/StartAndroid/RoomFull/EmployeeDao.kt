package com.example.testroom

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.testroom.entities.Car
import com.example.testroom.entities.Employee

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM tableName")
    fun getAll():List<Employee>
    @Query("SELECT * FROM tableName WHERE id = :id")
    fun getById(id:Long): Employee
    @Update
    fun update(employee: Employee)//обновит все поля ,если найдет id,который совпал
    @Delete
    fun delete(employee: Employee)
    @Insert
    fun insert(employee: Employee)
    @Insert
    fun insertMany(vararg employees:Employee)//вставлять сразу несколько работников(на update и depele аналогично)
    @Insert
    fun insertList(listEmployee:List<Employee>):List<Long>//можно вставлять списками,вернет список лонгов(на update и delete аналогично)


    @Query("SELECT * FROM tableName")//можно получать данные обернутые в лайф дату
    fun getAllLiveData():LiveData<List<Employee>>

    //поиск по диапазону
    @Query("SELECT * FROM tableName WHERE salary BETWEEN :min AND :max")
    fun getAllWithSalaryBetween(min:Int,max:Int)

    //поиск по имени или фамилии
    @Query("SELECT * FROM tableName WHERE name LIKE :name OR lastName LIKE :name")
    fun getAllWithNameLike(name:String)
    //поиск по списку айдишников
    @Query("SELECT * FROM tableName WHERE id IN (:idList)")
    fun getByIdList(idList:List<Long>)

    //обновление зарплат по списку айди
    @Query("UPDATE tableName SET salary = :newSalary WHERE id IN (:idList)")
    fun updateSalaryByIdList(idList:List<Long>,newSalary:Int)
    //удаление по списку айди
    @Query("DELETE FROM tableName WHERE id IN (:idList)")
    fun deleteSalaryByIdList(idList:List<Long>)



}