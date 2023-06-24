package br.com.erudio.Kotlin_REST_API.controllers

import br.com.erudio.Kotlin_REST_API.models.Person
import br.com.erudio.Kotlin_REST_API.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/person")
class PersonController {

    @Autowired
    private lateinit var service: PersonService

    @RequestMapping(
            value = ["/{id}"],
            method = [RequestMethod.GET],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun findById(@PathVariable(value="id") id: Long): Person {
        return service.findById(id)
    }

}
