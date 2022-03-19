package com.example.sahabss.data.remote.dto.employee.update

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.example.sahabss.data.remote.dto.employee.data.EmployeeDataDTO

@Keep
data class UpdateEmployeeDTO(
    @SerializedName("data")
    val `data`: List<EmployeeDataDTO>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)