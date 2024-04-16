package io.github.lucaspaixaodev.poxhexaddclean.domain.exceptions

open class DomainException(
    val errorCode: String,
    message: String,
) : RuntimeException(message)

class ResourceNotFoundException(
    errorCode: String,
    message: String
) : DomainException(errorCode = errorCode, message = message)

class InvalidArgumentException(
    errorCode: String,
    message: String,
) : DomainException(errorCode = errorCode, message = message)

class ResourceAlreadyExistsException(
    errorCode: String,
    message: String,
) : DomainException(errorCode = errorCode, message = message)
