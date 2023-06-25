package br.com.erudio.Kotlin_REST_API.unittests.mocks

import java.util.ArrayList
import br.com.erudio.Kotlin_REST_API.data.dto.v1.PersonDTO
import br.com.erudio.Kotlin_REST_API.models.Person

class MockPerson {
    fun mockEntity(): Person {
        return mockEntity(0)
    }

    fun mockVO(): PersonDTO {
        return mockVO(0)
    }

    fun mockEntityList(): ArrayList<Person> {
        val persons: ArrayList<Person> = ArrayList<Person>()
        for (i in 0..13) {
            persons.add(mockEntity(i))
        }
        return persons
    }

    fun mockVOList(): ArrayList<PersonDTO> {
        val persons: ArrayList<PersonDTO> = ArrayList()
        for (i in 0..13) {
            persons.add(mockVO(i))
        }
        return persons
    }

    fun mockEntity(number: Int): Person {
        val person = Person()
        person.address = "Address Test$number"
        person.firstName = "First Name Test$number"
        person.gender = if (number % 2 == 0) "Male" else "Female"
        person.id = number.toLong()
        person.lastName = "Last Name Test$number"
        return person
    }

    fun mockVO(number: Int): PersonDTO {
        val person = PersonDTO()
        person.address = "Address Test$number"
        person.firstName = "First Name Test$number"
        person.gender = if (number % 2 == 0) "Male" else "Female"
        person.key = number.toLong()
        person.lastName = "Last Name Test$number"
        return person
    }
}
