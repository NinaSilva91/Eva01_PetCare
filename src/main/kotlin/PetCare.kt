package org.example

import kotlinx.coroutines.delay

class PetCare {

    val nombre = "PetCare"

    val boxes = mutableListOf<Box>()

    val pacientes = mutableListOf<Paciente>()

    val historial = mutableListOf<Ticket>()

    var numeroTicket = 1

    init {
        for (i in 1..10) {
            boxes.add(Box(i))
        }
    }

    fun buscarBoxLibre(): Box? {

        for (box in boxes) {

            when (box.estado) {

                is EstadoBox.Libre ->
                    return box

                is EstadoBox.EnAtencion ->
                    println("Box ${box.numero} está en atención")

                is EstadoBox.EnProceso ->
                    println("Box ${box.numero} está en proceso")

                is EstadoBox.FueraDeServicio ->
                    println("Box ${box.numero} está fuera de servicio")
            }
        }

        return null
    }

    suspend fun registrarEntrada(paciente: Paciente) {

        val boxLibre = buscarBoxLibre()

        if (boxLibre == null) {
            println("Error: No hay boxes disponibles")
            return
        }

        boxLibre.estado = EstadoBox.EnProceso("Registrando entrada")

        println("Registrando entrada de ${paciente.nombre}")
        println("Box asignado: ${boxLibre.numero}")
        println("Estado: En proceso")

        delay(3000)

        boxLibre.estado = EstadoBox.EnAtencion(paciente)

        pacientes.add(paciente)

        println("Entrada registrada correctamente")
        println("Box ${boxLibre.numero}: En atención")
        println("Paciente: ${paciente.nombre}")
    }

    fun mostrarBoxes() {

        println("Sistema: $nombre")
        println("Cantidad de boxes: ${boxes.size}")
        println()

        for (box in boxes) {
            box.mostrarEstado()
        }
    }
    suspend fun registrarSalida(codigo: String, tiempoMinutos: Int) {

        var boxEncontrado: Box? = null
        var pacienteEncontrado: Paciente? = null

        for (box in boxes) {

            when (val estadoActual = box.estado) {

                is EstadoBox.Libre -> {
                }

                is EstadoBox.EnAtencion -> {

                    if (estadoActual.paciente.codigo == codigo) {
                        boxEncontrado = box
                        pacienteEncontrado = estadoActual.paciente
                    }
                }

                is EstadoBox.EnProceso -> {
                    println("Box ${box.numero} está en proceso")
                }

                is EstadoBox.FueraDeServicio -> {
                    println("Box ${box.numero} está fuera de servicio")
                }
            }
        }

        if (boxEncontrado == null || pacienteEncontrado == null) {
            println("Error: Paciente no encontrado")
            return
        }

        boxEncontrado.estado = EstadoBox.EnProceso("Calculando tarifa")

        println("Calculando salida de ${pacienteEncontrado.nombre}")
        println("Box: ${boxEncontrado.numero}")
        println("Estado: En proceso")

        delay(6500)

        val monto = pacienteEncontrado.calcularTarifa(tiempoMinutos)

        val ticket = Ticket(
            numeroTicket,
            pacienteEncontrado,
            tiempoMinutos,
            monto
        )

        historial.add(ticket)

        println()
        println("Salida registrada correctamente")
        println("Ticket: ${ticket.numero}")
        println("Paciente: ${pacienteEncontrado.nombre}")
        println("Tiempo: ${ticket.tiempoMinutos} minutos")
        println("Monto: $${ticket.monto}")

        numeroTicket++

        boxEncontrado.estado = EstadoBox.Libre("El box está disponible")

        println("Box ${boxEncontrado.numero}: Libre")
    }
}