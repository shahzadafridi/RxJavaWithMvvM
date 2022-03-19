package com.example.sahabss.data.remote.model

import com.google.gson.annotations.SerializedName


data class ApiResponse<T>(
    @SerializedName("error")
    val error: ApiError?,
    @SerializedName("result")
    val result: Result,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("server_time")
    val serverTime: Long,
    @SerializedName("data")
    val data: T?,
    @SerializedName("success")
    val success: String?,
    @SerializedName("errorCode")
    val errorCode: Int?,
    @SerializedName("errors")
    val errors: List<String>?
)

data class Result(
    @SerializedName("display_message")
    val displayMessage: String,
)

data class ApiError(
    @SerializedName("display_error_message")
    val displayErrorMessage: String,
    @SerializedName("error_code")
    val errorCode: Int,
    @SerializedName("error_message")
    val errorMessage: List<String>?,
)
