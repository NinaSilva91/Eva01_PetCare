package org.example

import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val petCare = PetCare()

    val paciente1 = Canino(
        "CA12CD",
        "Max",
        "Golden Retriever",
        "2026-09-26 10:00",
        "convenio"
    )

    val paciente2 = Canino(
        "CA99ZA",
        "Luna",
        "Labrador",
        "2026-09-26 10:05",
        "particular"
    )

    val paciente3 = Felino(
        "FE22TO",
        "Misi",
        "Siamés",
        "2026-09-26 10:10",
        "particular"
    )

    val paciente4 = Exotico(
        "EX44RG",
        "Loro",
        "Amazónico",
        "2026-09-26 10:15",
        "municipal",
        true
    )

    val paciente5 = Exotico(
        "EX77RG",
        "Iguana",
        "Verde",
        "2026-09-26 10:20",
        "particular",
        false
    )

    petCare.registrarEntrada(paciente1)
    petCare.registrarEntrada(paciente2)
    petCare.registrarEntrada(paciente3)
    petCare.registrarEntrada(paciente4)
    petCare.registrarEntrada(paciente5)

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

    petCare.mostrarBoxes()

    println()

    val boxesDisponibles = contarBoxesDisponibles(petCare.boxes)

    println("Boxes disponibles: $boxesDisponibles")

    println()

    val pacientesConvenio = obtenerPacientesConvenio(petCare.pacientes)

    println("Pacientes con convenio:")

    for (paciente in pacientesConvenio) {
        println("${paciente.codigo} - ${paciente.nombre}")
    }

    println()

    val recaudacion = calcularRecaudacion(petCare.historial)

    println("Recaudación total: $recaudacion")

    println()

    val promedio = calcularPromedioRecaudacion(petCare.historial)

    println("Promedio de recaudación por paciente: $promedio")

    println()

    val codigosFinalizados = obtenerCodigosFinalizados(petCare.historial)

    println("Pacientes finalizados:")

    for (codigo in codigosFinalizados) {
        println(codigo)
    }

    println()

    val pacienteMayorTiempo =
        obtenerPacienteMayorTiempo(petCare.historial)

    if (pacienteMayorTiempo != null) {

        println("Paciente con mayor tiempo de uso:")
        println("Paciente: ${pacienteMayorTiempo.paciente.nombre}")
        println("Código: ${pacienteMayorTiempo.paciente.codigo}")
        println("Tiempo: ${pacienteMayorTiempo.tiempoMinutos} minutos")
    }

    println()

    val recaudacionCaninos =
        calcularRecaudacionCaninos(petCare.historial)

    val recaudacionFelinos =
        calcularRecaudacionFelinos(petCare.historial)

    val recaudacionExoticos =
        calcularRecaudacionExoticos(petCare.historial)

    println("Recaudación por tipo:")
    println("Caninos: $recaudacionCaninos")
    println("Felinos: $recaudacionFelinos")
    println("Exóticos: $recaudacionExoticos")

    println()

    val tipoMayorRecaudacion =
        obtenerTipoMayorRecaudacion(petCare.historial)

    println("Tipo con mayor recaudación: $tipoMayorRecaudacion")

    println()

    mostrarInformeCierre(
        petCare.historial,
        petCare.boxes
    )
}