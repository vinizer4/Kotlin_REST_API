package br.com.erudio.Kotlin_REST_API.repositories

import br.com.erudio.Kotlin_REST_API.models.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<Person, Long?>
