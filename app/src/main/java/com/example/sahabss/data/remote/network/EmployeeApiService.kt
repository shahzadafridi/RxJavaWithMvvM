package com.example.sahabss.data.remote.network

import com.example.sahabss.data.remote.dto.employee.EmployeeDTO
import com.example.sahabss.data.remote.dto.employee.EmployeesDTO
import com.example.sahabss.data.remote.dto.employee.delete.DeleteEmployeeDTO
import com.example.sahabss.data.remote.dto.employee.update.UpdateEmployeeDTO
import com.example.sahabss.data.remote.model.ApiResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface EmployeeApiService {

    @GET("api/v1/employees")
    fun getEmployees(): Observable<EmployeesDTO>

    @GET("api/v1/employee/{id}")
    fun getEmployee(
        @Path("id") id:String
    ): Single<EmployeeDTO>

    @PUT("api/v1/update/{id}")
    fun updateEmployees(
        @Path("id") id:String
    ): Single<UpdateEmployeeDTO>

    @DELETE("api/v1/delete/{id}")
    fun deleteEmployees(
        @Path("id") id:String
    ): Single<DeleteEmployeeDTO>

}