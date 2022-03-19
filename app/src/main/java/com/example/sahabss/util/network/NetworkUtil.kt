package com.example.sahabss.data.util

sealed class Error : Exception() {

    object NoInternetConnection : Error()
    object Timeout : Error()
    object Other : Error()

    sealed class NetworkError : Error() {
        object Unauthorized : NetworkError()
        object Forbidden : NetworkError()
        object NotFound : NetworkError()
        object InvalidResponse : NetworkError()
        object Other : NetworkError()
    }

    data class ApiError(
        override val message: String?,
        val code: Int,
    ) : Error()

    val displayMessage: String?
        get() = when (this) {
            NetworkError.Forbidden -> "Sorry, you should not be here"
            NetworkError.Unauthorized -> "Sorry, you are not authorized"
            NoInternetConnection -> "You don't have internet connection"
            Timeout -> "Your internet connection is too slow"
            is ApiError -> this.message
            else -> "Some error occurred"
        }
}