package io.github.lucaspaixaodev.poxhexaddclean.domain.repositories

import io.github.lucaspaixaodev.poxhexaddclean.domain.entities.person.Person

fun interface PersonRepository {

    fun save(person: Person): Person
}
