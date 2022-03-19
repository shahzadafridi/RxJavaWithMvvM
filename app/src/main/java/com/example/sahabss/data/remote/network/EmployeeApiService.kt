package com.example.sahabss.data.remote.network

import com.example.sahabss.data.remote.dto.employee.EmployeeDTO
import com.example.sahabss.data.remote.dto.employee.EmployeesDTO
import com.example.sahabss.data.remote.dto.employee.delete.DeleteEmployeeDTO
import com.example.sahabss.data.remote.dto.employee.update.UpdateEmployeeDTO
import com.example.sahabss.data.remote.model.ApiResponse
import retrofit2.http.*

interface EmployeeApiService {

    @GET("api/v1/employees")
    suspend fun getEmployees(): ApiResponse<EmployeesDTO>

    @GET("api/v1/employee/{id}")
    suspend fun getEmployee(
        @Path("id") id:String
    ): ApiResponse<EmployeeDTO>

    @PUT("api/v1/update/{id}")
    suspend fun updateEmployees(
        @Path("id") id:String
    ): ApiResponse<UpdateEmployeeDTO>

    @DELETE("api/v1/delete/{id}")
    suspend fun deleteEmployees(
        @Path("id") id:String
    ): ApiResponse<DeleteEmployeeDTO>

}