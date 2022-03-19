package com.example.sahabss.data.util


sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure(val error: Error) : Resource<Nothing>()
}

sealed class Error : Exception() {
    object NoInternetConnection : Error()
    object Timeout : Error()
    sealed class NetworkError : Error() {
        object Unauthorized : NetworkError()
        object Forbidden : NetworkError()
        object NotFound : NetworkError()
        object InvalidResponse : NetworkError()
        object Other : NetworkError()
    }

    data class ApiError(
        override val message: String,
        val code: Int,
    ) : Error()

    object Other : Error()

    val displayMessage: String
        get() = when (this) {
            is ApiError -> this.message
            else -> "Some error occurred"
        }
}