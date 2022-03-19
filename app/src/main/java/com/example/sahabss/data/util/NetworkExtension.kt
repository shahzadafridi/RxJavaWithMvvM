package com.example.sahabss.data.util

import com.example.sahabss.data.remote.dto.api.ApiErrorDTO
import com.example.sahabss.data.remote.model.ApiResponse
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import java.io.EOFException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T> makeNetworkRequest(request: suspend () -> ApiResponse<T>): Resource<T> {
    try {
        val response = request.invoke()
        return when {
            response.error == null && response.data != null -> {
                Resource.Success(response.data)
            }
            response.error != null -> {
                Resource.Failure(
                    error = Error.ApiError(
                        code = response.error.errorCode,
                        message = response.error.displayErrorMessage,
                    )
                )
            }
            else -> Resource.Failure(Error.Other)
        }
    } catch (t: Throwable) {
        t.printStackTrace()
        return t.toNetworkResourceFailure()
    }
}

fun Throwable?.toNetworkResourceFailure(): Resource.Failure {
    return when (this) {
        is ConnectException,
        is UnknownHostException -> Resource.Failure(Error.NoInternetConnection)
        is SocketTimeoutException -> Resource.Failure(Error.Timeout)
        is HttpException -> {
            try {
                val response = this.response()?.errorBody()
                val errorResponse = Gson().fromJson(response?.string(), ApiErrorDTO::class.java)
                var message = "Some error has occurred"
                errorResponse.error?.let { message = it }
                errorResponse.message?.let { message = it }
                Resource.Failure(
                    Error.ApiError(
                        message = message,
                        code = this.code()
                    )
                )
            } catch (e: JsonSyntaxException) {
                Resource.Failure(
                    Error.ApiError(
                        message = "Some error has occurred",
                        code = this.code()
                    )
                )
            }
        }
        is EOFException -> Resource.Failure(Error.NetworkError.InvalidResponse)
        else -> Resource.Failure(Error.NetworkError.Other)
    }
}