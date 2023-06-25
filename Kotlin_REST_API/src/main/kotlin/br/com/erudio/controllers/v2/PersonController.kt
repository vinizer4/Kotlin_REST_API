package br.com.erudio.controllers.v2

import br.com.erudio.services.PersonService
import br.com.erudio.utils.MediaType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import br.com.erudio.data.dto.v2.PersonDTO as PersonDTOV2

@RestController
@RequestMapping("/api/person/v2")
class PersonControllerV2 {

    @Autowired
    private lateinit var service: PersonService

    @PostMapping(
            consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
            produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML]
    )
    @Operation(summary = "Adds a new Person", description = "Adds a new Person",
            tags = ["People"],
            responses = [
                ApiResponse(
                        description = "Success",
                        responseCode = "200",
                        content = [
                            Content(schema = Schema(implementation = PersonDTOV2::class))
                        ]
                ),
                ApiResponse(description = "Bad Request", responseCode = "400", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
                ApiResponse(description = "Unauthorized", responseCode = "401", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
                ApiResponse(description = "Internal Error", responseCode = "500", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
            ]
    )
    fun createV2(@RequestBody person: PersonDTOV2): PersonDTOV2 {
        return service.createV2(person)
    }

    @PutMapping(
            consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
            produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML]
    )
    @Operation(summary = "Updates a person's information", description = "Updates a person's information",
            tags = ["People"],
            responses = [
                ApiResponse(
                        description = "Success",
                        responseCode = "200",
                        content = [
                            Content(schema = Schema(implementation = PersonDTOV2::class))
                        ]
                ),
                ApiResponse(description = "No Content", responseCode = "204", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
                ApiResponse(description = "Bad Request", responseCode = "400", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
                ApiResponse(description = "Unauthorized", responseCode = "401", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
                ApiResponse(description = "Not Found", responseCode = "404", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
                ApiResponse(description = "Internal Error", responseCode = "500", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]),
            ]
    )
    fun updateV2(@RequestBody person: PersonDTOV2): PersonDTOV2 {
        return service.updateV2(person)
    }
}
