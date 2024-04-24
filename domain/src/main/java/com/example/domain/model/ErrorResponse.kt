package com.example.domain.model

sealed class ErrorResponse : Throwable(){

    data object Host: ErrorResponse()

    data object Network: ErrorResponse()

    data object Unknown: ErrorResponse()

}
