package org.example

data class Ticket(
    val numero: Int,
    val paciente: Paciente,
    val tiempoMinutos: Int,
    val monto: Double
)