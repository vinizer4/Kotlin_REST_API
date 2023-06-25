package br.com.erudio.Kotlin_REST_API.data.dto.v2

import br.com.erudio.Kotlin_REST_API.data.dto.v1.PersonDTO
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.github.dozermapper.core.Mapping
import org.springframework.hateoas.RepresentationModel
import java.util.*

@JsonPropertyOrder("id", "firstName", "lastName", "address", "gender", "birthDay")
class PersonDTO (
        @Mapping("id")
        @field:JsonProperty("id")
        var key: Long = 0,
        var firstName: String = "",
        var lastName: String = "",
        var address: String = "",
        var gender: String = "",
        var birthDay: Date? = null
): RepresentationModel<PersonDTO>()
