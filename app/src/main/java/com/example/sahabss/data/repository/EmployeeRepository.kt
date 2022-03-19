package com.example.sahabss.data.repository

import com.example.sahabss.data.remote.model.employee.Employee
import com.example.sahabss.util.UiStateResource

interface EmployeeRepository {

    suspend fun getEmployees(): UiStateResource<List<Employee>>

    suspend fun getEmployee(id: String): UiStateResource<Employee>

    suspend fun updateEmployee(id: String): UiStateResource<String>

    suspend fun deleteEmployee(id: String): UiStateResource<String>
}