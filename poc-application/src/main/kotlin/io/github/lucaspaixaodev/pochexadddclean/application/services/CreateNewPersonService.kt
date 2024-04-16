package io.github.lucaspaixaodev.pochexadddclean.application.services

import io.github.lucaspaixaodev.poxhexaddclean.domain.services.CreatePersonService
import io.github.lucaspaixaodev.poxhexaddclean.domain.services.CreatePersonServiceInput
import io.github.lucaspaixaodev.poxhexaddclean.domain.services.NotificationService
import io.github.lucaspaixaodev.poxhexaddclean.domain.services.NotificationServiceInput
import java.time.LocalDate
import java.util.UUID
import org.springframework.stereotype.Component

// serviços da aplicação, orquestram ações de serviços de domínio
@Component
class CreateNewPersonUseCase(
    private val createPersonService: CreatePersonService,
    private val notificationService: NotificationService
) {

    fun execute(input: CreateNewPersonUseCaseInput): CreateNewPersonUseCaseOutput {
        val createPersonServiceInput = createPersonServiceInput(input)

        val output = createPersonService.create(createPersonServiceInput)
        val newPersonId = output.id

        val notificationServiceInput = createNotificationServiceInput(newPersonId, input)
        notificationService.send(notificationServiceInput)

        return CreateNewPersonUseCaseOutput(newPersonId)
    }

    private fun createNotificationServiceInput(
        newPersonId: UUID,
        input: CreateNewPersonUseCaseInput
    ) = NotificationServiceInput(
        message = "Welcome to our platform $newPersonId!",
        recipient = input.email
    )

    private fun createPersonServiceInput(input: CreateNewPersonUseCaseInput) =
        CreatePersonServiceInput(
            name = input.name,
            birthDate = input.birthDate,
            email = input.email,
            address = CreatePersonServiceInput.Address(
                street = input.address.street,
                number = input.address.number,
                city = input.address.city,
                state = input.address.state,
                country = input.address.country
            )
        )
}

data class CreateNewPersonUseCaseInput(
    val name: String,
    val birthDate: LocalDate,
    val email: String,
    val address: Address
) {
    data class Address(
        val street: String,
        val number: Int,
        val city: String,
        val state: String,
        val country: String
    )
}

data class CreateNewPersonUseCaseOutput(
    val personId: UUID
)