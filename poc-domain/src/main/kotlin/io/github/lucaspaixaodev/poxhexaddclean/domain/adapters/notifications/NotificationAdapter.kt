package io.github.lucaspaixaodev.poxhexaddclean.domain.adapters.notifications

fun interface NotificationAdapter {

    fun send(message: String, recipient: String)
}