package br.com.erudio.Kotlin_REST_API.integrationstests.dto
data class PersonDTO (

        var id: Long = 0,
        var firstName: String = "",
        var lastName: String = "",
        var address: String = "",
        var gender: String = ""
)
