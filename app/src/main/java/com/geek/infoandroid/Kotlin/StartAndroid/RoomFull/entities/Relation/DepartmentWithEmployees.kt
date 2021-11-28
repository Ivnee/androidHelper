package com.example.testroom.entities.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.testroom.entities.Employee

//это не Entity а обычный класс с полями,которые мы ожидаем от запроса
//получим id и name(поля deprtment) , а employess подтянутся автоматически
data class DepartmentWithEmployees(
    @Embedded
    val department: Department,
    @Relation(parentColumn = "id"//это айди  department(тоесть нашего класса)
        , entityColumn = "department_id"//department_id это id работников,которых мы будем подтягивать
        , entity = Employee::class)//необязательный параметр,используется если тип возвращаемых данных не сам энтити класс,а класс ,который содержит только часть полей нашего Employee
    val employees: List<Employee>//списко сотрудников этого отдела
)