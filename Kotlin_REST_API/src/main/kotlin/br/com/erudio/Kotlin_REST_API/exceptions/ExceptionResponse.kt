package br.com.erudio.Kotlin_REST_API.exceptions

import java.util.*

class ExceptionResponse (
        val timestamp: Date,
        val message: String?,
        val details: String
)

