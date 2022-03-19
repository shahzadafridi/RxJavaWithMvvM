package com.example.sahabss.data.remote.dto.employee.data

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class EmployeeDataDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("employee_age")
    val employeeAge: Int,
    @SerializedName("employee_name")
    val employeeName: String,
    @SerializedName("employee_salary")
    val employeeSalary: Int,
    @SerializedName("profile_image")
    val profileImage: String
)