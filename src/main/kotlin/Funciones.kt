package org.example

fun validarCodigo(codigo: String): Boolean {

    if (codigo.length != 6) {
        return false
    }

    // Verifica el formato: 2 letras, 2 numeros y 2 letras.
    return codigo.matches(Regex("[A-Za-z]{2}[0-9]{2}[A-Za-z]{2}"))
}

fun validarTipoDueno(tipoDueno: String): Boolean {

    if (tipoDueno == "particular") {
        return true
    }

    if (tipoDueno == "convenio") {
        return true
    }

    if (tipoDueno == "municipal") {
        return true
    }

    return false
}

fun contarBoxesDisponibles(boxes: List<Box>): Int {

    return boxes.filter {
        it.estado is EstadoBox.Libre
    }.size
}

fun obtenerPacientesConvenio(historial: List<Ticket>): List<Paciente> {
    return historial.filter {
            it.paciente.tipoDueno == "convenio"
        }.map {
            it.paciente
        }
}

fun calcularRecaudacion(historial: List<Ticket>): Double {
    return historial.sumOf {
        it.monto
    }
}

fun calcularPromedioRecaudacion(historial: List<Ticket>): Double {
    if (historial.size == 0) {
        return 0.0
    }
    return historial.sumOf {
        it.monto
    } / historial.size
}

fun obtenerCodigosFinalizados(historial: List<Ticket>): List<String> {
    return historial.map {
        it.paciente.codigo
    }
}

fun obtenerPacienteMayorTiempo(historial: List<Ticket>): Ticket? {
    if (historial.size == 0) {
        return null
    }
    var ticketMayor = historial[0]

    // Compara los tiempos para encontrar el ticket con mayor duracion.
    for (ticket in historial) {

        if (ticket.tiempoMinutos > ticketMayor.tiempoMinutos) { ticketMayor = ticket
        }
    }
    return ticketMayor
}

fun calcularRecaudacionCaninos(historial: List<Ticket>
): Double {
    return historial
        .filter { it.paciente is Canino }
        .sumOf { it.monto }
}

fun calcularRecaudacionFelinos(historial: List<Ticket>): Double {
    return historial.filter { it.paciente is Felino }.sumOf { it.monto }
}

fun calcularRecaudacionExoticos(historial: List<Ticket>): Double {
    return historial.filter { it.paciente is Exotico }.sumOf { it.monto }
}

fun obtenerTipoMayorRecaudacion(historial: List<Ticket>): String
{
    val caninos = calcularRecaudacionCaninos(historial)
    val felinos = calcularRecaudacionFelinos(historial)
    val exoticos = calcularRecaudacionExoticos(historial)

    // Compara la recaudacion de cada tipo y devuelve la mayor.
    if (caninos >= felinos && caninos >= exoticos) {
        return "Canino"
    }
    if (felinos >= caninos && felinos >= exoticos) {
        return "Felino"
    }

    return "Exótico"
}

fun mostrarInformeCierre(historial: List<Ticket>, boxes: List<Box>) {

    println()
    println(" INFORME DE CIERRE ")

    for (ticket in historial) {

        val tipo = when (ticket.paciente) {
            is Canino -> "Canino"
            is Felino -> "Felino"
            is Exotico -> "Exotico"
            else -> "Desconocido"
        }

        println("Ticket: ${ticket.numero}")
        println("Tipo: $tipo")
        println("Codigo: ${ticket.paciente.codigo}")

        if (ticket.paciente is Exotico) {
            if (ticket.paciente.silvestre) {
                println("Silvestre: Sí")
            } else {
                println("Silvestre: No")
            }
        }

        println("Tiempo: ${ticket.tiempoMinutos} minutos")
        println("Monto: $${ticket.monto}")
        println("---------------------------------------")
    }

    val total = calcularRecaudacion(historial)
    val cantidad = historial.size
    val promedio = calcularPromedioRecaudacion(historial)
    val mayor = obtenerTipoMayorRecaudacion(historial)
    val disponibles = contarBoxesDisponibles(boxes)

    println("Recaudacion total: $total")
    println("Pacientes atendidos: $cantidad")
    println("Promedio por paciente: $promedio")
    println("Tipo con mayor recaudacion: $mayor")
    println("Boxes disponibles al cierre: $disponibles")
}