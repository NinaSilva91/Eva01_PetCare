package org.example

fun validarCodigo(codigo: String): Boolean {

    if (codigo.length != 6) {
        return false
    }

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