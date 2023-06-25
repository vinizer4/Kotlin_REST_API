package br.com.erudio.Kotlin_REST_API.data.dto.v2

import br.com.erudio.Kotlin_REST_API.data.dto.v1.PersonDTO
import com.github.dozermapper.core.Mapping
import org.springframework.hateoas.RepresentationModel
import java.util.*

class PersonDTO (
        @Mapping("id")
        var key: Long = 0,
        var firstName: String = "",
        var lastName: String = "",
        var address: String = "",
        var gender: String = "",
        var birthDay: Date? = null
): RepresentationModel<PersonDTO>()
