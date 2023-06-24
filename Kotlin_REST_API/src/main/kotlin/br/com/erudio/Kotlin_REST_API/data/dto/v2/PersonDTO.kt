package br.com.erudio.Kotlin_REST_API.data.dto.v2

import java.util.*

class PersonDTO (
        var id: Long = 0,
        var firstName: String = "",
        var lastName: String = "",
        var address: String = "",
        var gender: String = "",
        var birthDay: Date? = null
)
