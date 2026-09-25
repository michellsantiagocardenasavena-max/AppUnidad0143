package com.example.appunidad014_3

import java.io.Serializable

class Cotizacion : Serializable {

    var folio: Int = 0
    var descripcion: String = ""
    var porPagoInc: Float = 0.0f
    var precio: Float = 0.0f
    var plazos: Int = 0

    fun calcularPagoInicial(): Float {
        return precio * (porPagoInc / 100)
    }

    fun calcularTotalFin(): Float {
        return precio - calcularPagoInicial()
    }

    fun calcularPagoMensual(): Float {
        return calcularTotalFin() / plazos
    }

    fun generarFolio(): Int {
        val numero = (1..100).random()
        return numero
    }
}