package io.github.lucaspaixaodev.poxhexaddclean.domain.services

import io.github.lucaspaixaodev.poxhexaddclean.domain.adapters.notifications.NotificationAdapter

class NotificationService(
    private val notificationAdapter: NotificationAdapter
) {

    fun send(input: NotificationServiceInput) {
        notificationAdapter.send(
            message = input.message,
            recipient = input.recipient
        )
    }
}

data class NotificationServiceInput(
    val message: String,
    val recipient: String
) {
    init {
        require(message.isNotBlank()) { "Message cannot be blank" }
        require(recipient.isNotBlank()) { "Recipient cannot be blank" }
    }
}