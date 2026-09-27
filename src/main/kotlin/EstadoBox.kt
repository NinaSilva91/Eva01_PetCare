package org.example

sealed class EstadoBox {

    data class Libre(
        val mensaje: String) : EstadoBox()

    data class EnAtencion(
        val paciente: Paciente) : EstadoBox()

    data class EnProceso(
        val mensaje: String) : EstadoBox()

    data class FueraDeServicio(
        val mensaje: String) : EstadoBox()
}