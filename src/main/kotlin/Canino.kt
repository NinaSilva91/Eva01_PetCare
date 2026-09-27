package org.example
class Canino(
    codigo: String,
    nombre: String,
    especie: String,
    fechaIngreso: String,
    tipoDueno: String
) : Paciente(codigo, nombre, especie, fechaIngreso, tipoDueno) {

    override fun calcularTarifa(tiempoMinutos: Int): Double {

        val tarifaBase = 12000.0
        var monto = tarifaBase * tiempoMinutos / 60

        if (tipoDueno == "convenio") {
            monto = monto * 0.80
        }

        return monto
    }

    override fun mostrarInformacion(): String {
        return "${super.mostrarInformacion()}, Tipo: Canino"
    }
}