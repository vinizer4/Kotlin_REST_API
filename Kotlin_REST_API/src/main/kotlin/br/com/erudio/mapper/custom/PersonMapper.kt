package br.com.erudio.mapper.custom

import br.com.erudio.models.Person
import org.springframework.stereotype.Service
import br.com.erudio.data.dto.v2.PersonDTO as PersonDTOV2

@Service
class PersonMapper {

    fun mapEntityToVO(person: Person): PersonDTOV2 {
        val vo = PersonDTOV2()

        vo.key = person.id
        vo.firstName = person.firstName
        vo.birthDay = person.birthDay
        vo.lastName = person.lastName
        vo.address = person.address
        vo.gender = person.gender

        return vo
    }

    fun mapVOToEntity(person: PersonDTOV2): Person {
        val entity = Person()

        entity.id = person.key
        entity.firstName = person.firstName
        entity.birthDay = person.birthDay
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        return entity
    }
}
