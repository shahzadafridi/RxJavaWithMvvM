package com.example.sahabss.data.util

import com.example.sahabss.data.remote.dto.api.ApiErrorDTO
import com.example.sahabss.util.UiStateResource
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import java.io.EOFException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable?.toNetworkResourceFailure(): UiStateResource.Failure {
    return when (this) {
        is ConnectException,
        is UnknownHostException -> UiStateResource.Failure(Error.NoInternetConnection)
        is SocketTimeoutException -> UiStateResource.Failure(Error.Timeout)
        is HttpException -> {
            try {
                val response = this.response()?.errorBody()
                val errorResponse = Gson().fromJson(response?.string(), ApiErrorDTO::class.java)
                UiStateResource.Failure(
                    Error.ApiError(
                        message = errorResponse.message,
                        code = this.code()
                    )
                )
            } catch (e: JsonSyntaxException) {
                UiStateResource.Failure(
                    Error.ApiError(
                        message = this.message() ?: "",
                        code = this.code()
                    )
                )
            }
        }
        is EOFException -> UiStateResource.Failure(Error.NetworkError.InvalidResponse)
        else -> UiStateResource.Failure(Error.NetworkError.Other)
    }
}