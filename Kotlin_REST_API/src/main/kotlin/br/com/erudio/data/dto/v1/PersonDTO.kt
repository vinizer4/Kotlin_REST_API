package br.com.erudio.data.dto.v1

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.github.dozermapper.core.Mapping
import org.springframework.hateoas.RepresentationModel

@JsonPropertyOrder("id", "address", "firstName", "lastName", "gender")
class PersonDTO (
        @Mapping("id")
        @field:JsonProperty("id")
        var key: Long = 0,
        @field:JsonProperty("firstName")
        var firstName: String = "",
        @field:JsonProperty("lastName")
        var lastName: String = "",
        var address: String = "",
        var gender: String = ""
) : RepresentationModel<PersonDTO>()
