package br.com.erudio.Kotlin_REST_API.services

import br.com.erudio.Kotlin_REST_API.data.dto.v1.PersonDTO
import br.com.erudio.Kotlin_REST_API.data.dto.v2.PersonDTO as PersonDTOV2
import br.com.erudio.Kotlin_REST_API.exceptions.ResourceNotFoundException
import br.com.erudio.Kotlin_REST_API.mapper.DozerMapper
import br.com.erudio.Kotlin_REST_API.models.Person
import br.com.erudio.Kotlin_REST_API.repositories.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll(): List<PersonDTO> {
        logger.info("Finding all people!")
        val persons = repository.findAll()
        return DozerMapper.parseListObjects(persons, PersonDTO::class.java)
    }

    fun findById(id: Long): PersonDTO {
        logger.info("Finding one person!")

        val person = repository.findById(id).orElseThrow {
            ResourceNotFoundException(
                    "No records found for this ID!")
        }

        return DozerMapper.parseObject(person, PersonDTO::class.java)
    }

    fun create(person: PersonDTO): PersonDTO {
        logger.info("Creating one person with name ${person.firstName}!")
        val entity: Person = DozerMapper.parseObject(person, Person::class.java)
        return DozerMapper.parseObject(repository.save(entity), PersonDTO::class.java)
    }

    fun createV2(person: PersonDTOV2): PersonDTOV2 {
        logger.info("Creating one person with name ${person.firstName}!")
        val entity: Person = DozerMapper.parseObject(person, Person::class.java)
        return DozerMapper.parseObject(repository.save(entity), PersonDTOV2::class.java)
    }

    fun update(person: PersonDTO): PersonDTO {
        logger.info("Updating one person with ID ${person.id}!")
        val entity: Person = repository.findById(person.id)
                .orElseThrow {
            ResourceNotFoundException("No records found for this ID!")
        }

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        return DozerMapper.parseObject(repository.save(entity), PersonDTO::class.java)
    }

    fun delete(id: Long) {
        logger.info("Deleting one person with ID ${id}!")
        val entity: Person = repository.findById(id).orElseThrow {
            ResourceNotFoundException(
                    "No records found for this ID!")
        }
        repository.delete(entity)
    }

}
