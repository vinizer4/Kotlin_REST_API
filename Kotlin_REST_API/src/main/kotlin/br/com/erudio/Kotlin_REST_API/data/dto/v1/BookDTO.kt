package br.com.erudio.Kotlin_REST_API.data.dto.v1

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.github.dozermapper.core.Mapping
import jakarta.persistence.*
import org.springframework.hateoas.RepresentationModel
import java.util.*

@JsonPropertyOrder("id", "author", "launchDate", "price", "title")
data class BookDTO (

        @Mapping("id")
        @field:JsonProperty("id")
        var key: Long = 0,
        var author: String = "",
        var launchDate: Date? = null,
        var price: Double = 0.0,
        var title: String = "",
) : RepresentationModel<BookDTO>()
