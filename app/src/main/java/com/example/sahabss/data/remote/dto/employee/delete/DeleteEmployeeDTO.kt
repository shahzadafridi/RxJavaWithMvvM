package com.example.sahabss.data.remote.dto.employee.delete

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class DeleteEmployeeDTO(
    @SerializedName("data")
    val data: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)
