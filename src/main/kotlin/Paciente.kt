package org.example
open class Paciente(
    val codigo: String,
    val nombre: String,
    val especie: String,
    val fechaIngreso: String,
    val tipoDueno: String
) {

    open fun calcularTarifa(tiempoMinutos: Int): Double {
        return 0.0
    }

    open fun mostrarInformacion(): String {
        return "Código: $codigo, Nombre: $nombre, Especie: $especie, Dueño: $tipoDueno"
    }
}