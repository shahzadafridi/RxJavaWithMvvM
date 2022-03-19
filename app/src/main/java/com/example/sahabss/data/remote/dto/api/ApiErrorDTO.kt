package com.example.sahabss.data.remote.dto.api

import com.google.gson.annotations.SerializedName

data class ApiErrorDTO(
    @SerializedName("message")
    val message: String?,
    @SerializedName("statusCode")
    val statusCode: String?,
    @SerializedName("error")
    val error: String?
)