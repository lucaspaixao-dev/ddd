package io.github.lucaspaixaodev.poxhexaddclean.domain.services

import io.github.lucaspaixaodev.poxhexaddclean.domain.entities.person.Person
import io.github.lucaspaixaodev.poxhexaddclean.domain.repositories.PersonRepository
import java.time.LocalDate
import java.util.UUID

// serviço para criar uma pessoa, responsabilidade unica, apenas cria pessoa
class CreatePersonService(
    private val personRepository: PersonRepository
) {

    fun create(input: CreatePersonServiceInput): CreatePersonServiceOutput {
        val newPerson = createPersonAsPending(input)

        newPerson.activate()

        personRepository.save(newPerson)

        return CreatePersonServiceOutput(newPerson.id)
    }

    private fun createPersonAsPending(input: CreatePersonServiceInput) =
        Person.createAsPending(
            name = input.name,
            birthDate = input.birthDate,
            email = input.email,
            address = Person.Address(
                state = input.address.street,
                number = input.address.number,
                city = input.address.city,
                street = input.address.street,
                country = input.address.country
            )
        )
}


data class CreatePersonServiceInput(
    val name: String,
    val birthDate: LocalDate,
    val email: String,
    val address: Address
) {

    init {
        require(name.isNotBlank()) { "Name cannot be blank" }
        require(email.isNotBlank()) { "Email cannot be blank" }
        require(email.contains("@")) { "Email must contain @" }
    }

    data class Address(
        val street: String,
        val number: Int,
        val city: String,
        val state: String,
        val country: String
    ) {
        init {
            require(street.isNotBlank()) { "Street cannot be blank" }
            require(city.isNotBlank()) { "City cannot be blank" }
            require(state.isNotBlank()) { "State cannot be blank" }
            require(country.isNotBlank()) { "Country cannot be blank" }
        }

    }
}

data class CreatePersonServiceOutput(
    val id: UUID,
)