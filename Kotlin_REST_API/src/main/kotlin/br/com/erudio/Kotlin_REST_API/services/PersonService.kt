package br.com.erudio.Kotlin_REST_API.services

import br.com.erudio.Kotlin_REST_API.models.Person
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger

@Service
class PersonService {

    private val counter: AtomicLong = AtomicLong()

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findById(id: Long): Person {

        logger.info("Finding one person!")

        val person = Person()
        person.id = counter.incrementAndGet()
        person.firstName = "Vinicius"
        person.lastName = "Teixeira"
        person.address = "Goiania - Goias - Brasil"
        person.gender = "Male"

        return person
    }
}
