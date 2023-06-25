package br.com.erudio.Kotlin_REST_API.services

import br.com.erudio.Kotlin_REST_API.controllers.v1.PersonControllerV1
import br.com.erudio.Kotlin_REST_API.data.dto.v1.PersonDTO
import br.com.erudio.Kotlin_REST_API.exceptions.RequiredObjectIsNullException
import br.com.erudio.Kotlin_REST_API.data.dto.v2.PersonDTO as PersonDTOV2
import br.com.erudio.Kotlin_REST_API.exceptions.ResourceNotFoundException
import br.com.erudio.Kotlin_REST_API.mapper.DozerMapper
import br.com.erudio.Kotlin_REST_API.mapper.custom.PersonMapper
import br.com.erudio.Kotlin_REST_API.models.Person
import br.com.erudio.Kotlin_REST_API.repositories.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    @Autowired
    private lateinit var mapper: PersonMapper

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll(): List<PersonDTO> {
        logger.info("Finding all people!")
        val persons = repository.findAll()
        val dtos = DozerMapper.parseListObjects(persons, PersonDTO::class.java)

        for (person in dtos) {
            val withSelfRel = linkTo(PersonControllerV1::class.java).slash(person.key).withSelfRel()
            person.add(withSelfRel)
        }

        return dtos
    }

    fun findById(id: Long): PersonDTO {
        logger.info("Finding one person with ID $id!")

        val person = repository.findById(id).orElseThrow {
            ResourceNotFoundException(
                    "No records found for this ID!")
        }

        val personDTO: PersonDTO = DozerMapper.parseObject(person, PersonDTO::class.java)
        val withSelfRel = linkTo(PersonControllerV1::class.java).slash(personDTO.key).withSelfRel()
        personDTO.add(withSelfRel)
        return personDTO
    }

    fun create(person: PersonDTO?): PersonDTO {
        if (person == null) throw RequiredObjectIsNullException()
        logger.info("Creating one person with name ${person.firstName}!")
        val entity: Person = DozerMapper.parseObject(person, Person::class.java)
        val personDTO: PersonDTO = DozerMapper.parseObject(repository.save(entity), PersonDTO::class.java)
        val withSelfRel = linkTo(PersonControllerV1::class.java).slash(personDTO.key).withSelfRel()
        personDTO.add(withSelfRel)
        return personDTO
    }

    fun createV2(person: PersonDTOV2?): PersonDTOV2 {
        if (person == null) throw RequiredObjectIsNullException()
        logger.info("Creating one person with name ${person.firstName}!")
        val entity: Person = mapper.mapVOToEntity(person)
        val personDTO: PersonDTOV2 = mapper.mapEntityToVO(repository.save(entity))
        val withSelfRel = linkTo(PersonControllerV1::class.java).slash(personDTO.key).withSelfRel()
        personDTO.add(withSelfRel)
        return personDTO
    }

    fun update(person: PersonDTO?): PersonDTO {
        if (person == null) throw RequiredObjectIsNullException()
        logger.info("Updating one person with ID ${person.key}!")
        val entity: Person = repository.findById(person.key)
                .orElseThrow {
            ResourceNotFoundException("No records found for this ID!")
        }

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        val personDTO: PersonDTO = DozerMapper.parseObject(repository.save(entity), PersonDTO::class.java)
        val withSelfRel = linkTo(PersonControllerV1::class.java).slash(personDTO.key).withSelfRel()
        personDTO.add(withSelfRel)
        return personDTO
    }

    fun updateV2(person: PersonDTOV2): PersonDTOV2 {
        logger.info("Updating one person with ID ${person.key}!")
        val entity: Person = repository.findById(person.key)
                .orElseThrow {
                    ResourceNotFoundException("No records found for this ID!")
                }

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.birthDay = person.birthDay
        entity.gender = person.gender

        val personDTO: PersonDTOV2 = mapper.mapEntityToVO(repository.save(entity))
        val withSelfRel = linkTo(PersonControllerV1::class.java).slash(personDTO.key).withSelfRel()
        personDTO.add(withSelfRel)
        return personDTO
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
