package org.example

import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val petCare = PetCare()

    val paciente = Canino(
        "CA12CD",
        "Max",
        "Golden Retriever",
        "2026-09-26 10:00",
        "convenio"
    )

    petCare.registrarEntrada(paciente)

    println()

    petCare.mostrarBoxes()
}