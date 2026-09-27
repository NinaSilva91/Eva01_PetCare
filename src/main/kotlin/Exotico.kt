package org.example

class Exotico(
    codigo: String,
    nombre: String,
    especie: String,
    fechaIngreso: String,
    tipoDueno: String,
    val silvestre: Boolean
) : Paciente(codigo, nombre, especie, fechaIngreso, tipoDueno) {

    override fun calcularTarifa(tiempoMinutos: Int): Double {
        val tarifaBase = 20000.0
        var monto = tarifaBase * tiempoMinutos / 60

        if (silvestre) { monto = monto * 1.30 }
        return monto
    }
    override fun mostrarInformacion(): String {
        val tipoSilvestre = if (silvestre) { "Si" } else { "No" }
        return "${super.mostrarInformacion()}, Tipo: Exotico, Silvestre: $tipoSilvestre"
    }
}