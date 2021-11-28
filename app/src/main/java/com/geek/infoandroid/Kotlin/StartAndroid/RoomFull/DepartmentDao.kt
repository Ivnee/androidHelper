package com.example.testroom

import androidx.room.Dao
import androidx.room.Query
import com.example.testroom.entities.relation.DepartmentWithEmployees

@Dao
interface DepartmentDao {
    @Query("SELECT id,name FROM department")
    fun getDepartmentWithEmployees():List<DepartmentWithEmployees>//этот метод получит департамент со всеми его работниками
}