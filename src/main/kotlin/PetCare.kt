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
                is EstadoBox.Libre -> {
                    return box
                }
                is EstadoBox.EnAtencion -> {
                }
                is EstadoBox.EnProceso -> {
                }
                is EstadoBox.FueraDeServicio -> {
                }
            }
        }
        return null
    }
    suspend fun registrarEntrada(paciente: Paciente) {
        if (!validarCodigo(paciente.codigo)) {
            println("Error: Codigo de atencion invalido")
            return
        }
        if (!validarTipoDueno(paciente.tipoDueno)) { println("Error: Tipo de dueno invalido")
            return
        }

        // Busca el primer box disponible para asignar al paciente.
        val boxLibre = buscarBoxLibre()

        if (boxLibre == null) { println("Error: No hay boxes disponibles")
            return
        }
        boxLibre.estado = EstadoBox.EnProceso("Registrando entrada")

        println("Registrando entrada de ${paciente.nombre}")
        println("Box asignado: ${boxLibre.numero}")
        println("Estado: En proceso")

        // Simula la espera de confirmacion del sensor de entrada.
        delay(3000)

        boxLibre.estado = EstadoBox.EnAtencion(paciente)

        pacientes.add(paciente)

        println("Entrada registrada correctamente")
        println("Box ${boxLibre.numero}: En atencion")
        println("Paciente: ${paciente.nombre}")
    }

    fun mostrarBoxes() {

        println("Sistema: $nombre")
        println("Cantidad de boxes: ${boxes.size}")
        println()

        for (box in boxes) {box.mostrarEstado()
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
                }
                is EstadoBox.FueraDeServicio -> {
                }
            }
        }

        if (boxEncontrado == null || pacienteEncontrado == null) {println("Error: Paciente no encontrado")
            return
        }

        boxEncontrado.estado = EstadoBox.EnProceso("Calculando tarifa")

        println("Calculando salida de ${pacienteEncontrado.nombre}")
        println("Box: ${boxEncontrado.numero}")
        println("Estado: En proceso")

        // Simula la comunicacion con el sensor durante la salida.
        delay(6500)

        try {
            var monto = pacienteEncontrado.calcularTarifa(tiempoMinutos)
            if (monto <= 0) {
                throw Exception(
                    "La tarifa no puede ser menor o igual a cero"
                )
            }
            monto = monto * 1.19
            if (pacienteEncontrado.tipoDueno == "municipal") {
                monto = monto * 0.50
            }
            val ticket = Ticket(numeroTicket, pacienteEncontrado, tiempoMinutos, monto)
            historial.add(ticket)

            println()
            println("Salida registrada correctamente")
            println("Ticket: ${ticket.numero}")
            println("Paciente: ${pacienteEncontrado.nombre}")
            println("Tiempo: ${ticket.tiempoMinutos} minutos")
            println("Monto final: $${ticket.monto}")
            numeroTicket++

            boxEncontrado.estado = EstadoBox.Libre("El box esta disponible")
            println("Box ${boxEncontrado.numero}: Libre")
        } catch (e: Exception) {
            println("Error al calcular la tarifa: ${e.message}")

            boxEncontrado.estado = EstadoBox.EnAtencion(pacienteEncontrado)
            println("Box ${boxEncontrado.numero}: En atencion")
            println("Paciente: ${pacienteEncontrado.nombre}")
        }
    }
}