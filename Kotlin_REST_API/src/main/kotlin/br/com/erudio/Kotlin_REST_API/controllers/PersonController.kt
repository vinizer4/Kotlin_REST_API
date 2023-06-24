package br.com.erudio.Kotlin_REST_API.controllers

import br.com.erudio.Kotlin_REST_API.data.dto.v1.PersonDTO
import br.com.erudio.Kotlin_REST_API.data.dto.v2.PersonDTO as PersonDTOV2
import br.com.erudio.Kotlin_REST_API.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person")
class PersonController {

    @Autowired
    private lateinit var service: PersonService

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAll(): List<PersonDTO> {
        return service.findAll()
    }

    @GetMapping(
            value = ["/{id}"],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun findById(@PathVariable(value="id") id: Long): PersonDTO {
        return service.findById(id)
    }

    @PostMapping(
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun create(@RequestBody person: PersonDTO): PersonDTO {
        return service.create(person)
    }

    @PostMapping(value = ["/v2"],
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun createV2(@RequestBody person: PersonDTOV2): PersonDTOV2 {
        return service.createV2(person)
    }

    @PutMapping(
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun update(@RequestBody person: PersonDTO): PersonDTO {
        return service.update(person)
    }

    @DeleteMapping(
            value = ["/{id}"],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun delete(@PathVariable(value="id") id: Long): ResponseEntity<*> {
        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }
}
