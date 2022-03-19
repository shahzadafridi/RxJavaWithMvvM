package com.example.sahabss.data.remote.model.employee

import androidx.annotation.Keep

@Keep
data class Employee(
    val id: Int,
    val employeeAge: Int,
    val employeeName: String,
    val employeeSalary: Int,
    val profileImage: String
)