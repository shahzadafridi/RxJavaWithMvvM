package com.example.sahabss.data.remote.dto.employee

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.example.sahabss.data.remote.dto.employee.data.EmployeeDataDTO

@Keep
data class EmployeeDTO(
    @SerializedName("data")
    val `data`: EmployeeDataDTO,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)