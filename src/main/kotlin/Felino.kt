package org.example

class Felino(
    codigo: String,
    nombre: String,
    especie: String,
    fechaIngreso: String,
    tipoDueno: String
) : Paciente(codigo, nombre, especie, fechaIngreso, tipoDueno) {

    override fun calcularTarifa(tiempoMinutos: Int): Double {

        if (tiempoMinutos < 20) {
            return 0.0
        }

        val tarifaBase = 9000.0
        return tarifaBase * tiempoMinutos / 60
    }

    override fun mostrarInformacion(): String {
        return "${super.mostrarInformacion()}, Tipo: Felino"
    }
}