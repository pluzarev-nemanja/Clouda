package com.example.domain.mapper

import android.net.http.HttpException
import com.example.domain.model.ErrorResponse
import java.io.IOException
import java.net.UnknownHostException

class ThrowableToErrorModelMapper : Mapper<Throwable, ErrorResponse> {

    override fun mappingObjects(input: Throwable): ErrorResponse =
        when (input) {
            is UnknownHostException -> ErrorResponse.Network
            is HttpException -> ErrorResponse.Host
            is IOException -> ErrorResponse.Network
            else -> ErrorResponse.Unknown
        }
}