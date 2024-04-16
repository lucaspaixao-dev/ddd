package io.github.lucaspaixaodev.poxhexaddclean.domain.entities.person

import java.time.LocalDate
import java.util.UUID

// agragate root
class Person private constructor(
    val id: UUID,
    val name: String,
    val birthDate: LocalDate,
    val email: String,
    val address: Address
) {
    var status: Status = Status.PENDING
        private set


    fun activate() {
        setStatus(Status.ACTIVE)
    }

    fun pending() {
        setStatus(Status.PENDING)
    }

    private fun setStatus(status: Status) {
        this.status = status
    }

    enum class Status(
        val value: String
    ) {
        PENDING("Pendente"),
        ACTIVE("Ativo")
    }

    // value object
    // imutavel
    data class Address(
        val street: String,
        val number: Int,
        val city: String,
        val state: String,
        val country: String
    )

    // factory
    companion object Factory {
        // cria um com status pendente
        fun createAsPending(
            name: String,
            birthDate: LocalDate,
            email: String,
            address: Address
        ): Person {
            validateBirthDate(birthDate)
            return create(UUID.randomUUID(), name, birthDate, email, address, Status.PENDING)
        }

        // restaura o objeto, geralmente o que vem do banco de dados
        fun restore(
            id: UUID,
            name: String,
            birthDate: LocalDate,
            email: String,
            address: Address,
            status: Status
        ): Person {
            validateBirthDate(birthDate)
            return create(id, name, birthDate, email, address, status)
        }

        private fun create(
            id: UUID,
            name: String,
            birthDate: LocalDate,
            email: String,
            address: Address,
            status: Status
        ): Person =
            Person(
                id = id,
                name = name,
                birthDate = birthDate,
                email = email,
                address = address
            ).apply {
                this.status = status
            }

        // a entidade sabe se alto validar
        private fun validateBirthDate(birthDate: LocalDate) {
            require(!birthDate.isAfter(LocalDate.now())) {
                throw IllegalArgumentException("Birth date cannot be in the future")
            }

            require(!birthDate.isBefore(LocalDate.of(1900, 1, 1))) {
                throw IllegalArgumentException("Birth date cannot be before 1900")
            }
        }
    }
}
