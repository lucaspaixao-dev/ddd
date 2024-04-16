package io.github.lucaspaixaodev.poxhexaddclean.infra.exceptions

open class InfraException(
    val errorCode: String,
    message: String,
    throwable: Throwable,
) : RuntimeException(message, throwable)

class DatabaseException(errorCode: String, message: String, throwable: Throwable) :
    InfraException(errorCode = errorCode, message = message, throwable = throwable)

class EventException(errorCode: String, message: String, throwable: Throwable) :
    InfraException(errorCode = errorCode, message = message, throwable = throwable)
