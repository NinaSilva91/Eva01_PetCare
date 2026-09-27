package org.example

class Box(
    val numero: Int
) {

    var estado: EstadoBox = EstadoBox.Libre("El box está disponible")

    fun mostrarEstado() {

        when (val estadoActual = estado) {

            is EstadoBox.Libre ->
                println("Box $numero: ${estadoActual.mensaje}")

            is EstadoBox.EnAtencion ->
                println("Box $numero: En atención - Paciente: ${estadoActual.paciente.nombre}")

            is EstadoBox.EnProceso ->
                println("Box $numero: En proceso - ${estadoActual.mensaje}")

            is EstadoBox.FueraDeServicio ->
                println("Box $numero: Fuera de servicio - ${estadoActual.mensaje}")
        }
    }
}