package br.com.erudio.Kotlin_REST_API.data.dto.v1

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.github.dozermapper.core.Mapping
import org.springframework.hateoas.RepresentationModel

@JsonPropertyOrder("id", "address", "first_name", "last_name", "gender")
class PersonDTO (
        @Mapping("id")
        @field:JsonProperty("id")
        var key: Long = 0,
        @field:JsonProperty("first_name")
        var firstName: String = "",
        @field:JsonProperty("last_name")
        var lastName: String = "",
        var address: String = "",

        @field:JsonIgnore
        var gender: String = ""
) : RepresentationModel<PersonDTO>()
