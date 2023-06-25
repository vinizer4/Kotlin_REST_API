package br.com.erudio.Kotlin_REST_API.exceptions

import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.FORBIDDEN)
class InvalidJwtAuthenticationException(exception: String?)
    : AuthenticationException(exception)
