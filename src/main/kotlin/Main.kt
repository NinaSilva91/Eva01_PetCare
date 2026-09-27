package org.example

import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val petCare = PetCare()

    println("PETCARE - Sistema veterinario")
    println("--------------------------------")
    println()

    // Creando pacientes
    val paciente1 = Canino("CA12CD", "Max", "Golden Retriever", "2026-09-26 10:00", "convenio")
    val paciente2 = Canino("CA99ZA", "Luna", "Labrador", "2026-09-26 10:05", "particular")
    val paciente3 = Felino("FE22TO", "Misi", "Siamés", "2026-09-26 10:10", "particular")
    val paciente4 = Exotico("EX44RG", "Loro", "Amazónico", "2026-09-26 10:15", "municipal", true)
    val paciente5 = Exotico("EX77RG", "Iguana", "Verde", "2026-09-26 10:20", "particular", false)

    println("[INGRESO DE PACIENTES]")
    println()

    petCare.registrarEntrada(paciente1)
    println()

    petCare.registrarEntrada(paciente2)
    println()

    petCare.registrarEntrada(paciente3)
    println()

    petCare.registrarEntrada(paciente4)
    println()

    petCare.registrarEntrada(paciente5)

    println()
    println("[SALIDA DE PACIENTES]")
    println()

    petCare.registrarSalida("CA12CD", 75)
    println()

    petCare.registrarSalida("CA99ZA", 180)
    println()

    petCare.registrarSalida("FE22TO", 18)
    println()

    petCare.registrarSalida("EX44RG", 120)
    println()

    petCare.registrarSalida("EX77RG", 45)

    println()
    println("[ESTADO DE LOS BOXES]")
    println()

    petCare.mostrarBoxes()

    println()
    println("[CONSULTAS DEL TURNO]")
    println()

    val boxesDisponibles = contarBoxesDisponibles(petCare.boxes)

    println("Boxes disponibles: $boxesDisponibles")
    println()

    val pacientesConvenio = obtenerPacientesConvenio(petCare.historial)

    println("Pacientes con convenio:")

    for (paciente in pacientesConvenio) {
        println("${paciente.codigo} - ${paciente.nombre}")
    }

    println()

    val recaudacion = calcularRecaudacion(petCare.historial)

    println("Recaudacion total: $recaudacion")
    println()

    val promedio = calcularPromedioRecaudacion(petCare.historial)

    println("Promedio de recaudacion por paciente: $promedio")
    println()

    val codigosFinalizados = obtenerCodigosFinalizados(petCare.historial)

    println("Pacientes finalizados:")

    for (codigo in codigosFinalizados) {
        println(codigo)
    }

    println()

    val pacienteMayorTiempo = obtenerPacienteMayorTiempo(petCare.historial)

    if (pacienteMayorTiempo != null) {
        println("Paciente con mayor tiempo de uso:")
        println("Paciente: ${pacienteMayorTiempo.paciente.nombre}")
        println("Codigo: ${pacienteMayorTiempo.paciente.codigo}")
        println("Tiempo: ${pacienteMayorTiempo.tiempoMinutos} minutos")
    }

    println()
    println("[RECAUDACION POR TIPO]")
    println()

    val recaudacionCaninos = calcularRecaudacionCaninos(petCare.historial)
    val recaudacionFelinos = calcularRecaudacionFelinos(petCare.historial)
    val recaudacionExoticos = calcularRecaudacionExoticos(petCare.historial)

    println("Caninos: $recaudacionCaninos")
    println("Felinos: $recaudacionFelinos")
    println("Exoticos: $recaudacionExoticos")

    println()

    val tipoMayorRecaudacion = obtenerTipoMayorRecaudacion(petCare.historial)

    println("Tipo con mayor recaudacion: $tipoMayorRecaudacion")

    println()
    println("[INFORME DE CIERRE]")
    println()

    mostrarInformeCierre(petCare.historial, petCare.boxes)

    println()
    println("Fin del programa")
}