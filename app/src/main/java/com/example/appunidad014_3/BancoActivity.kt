package com.example.appunidad014_3

import java.io.Serializable

class BancoActivity : Serializable {

    var numCuenta: String = ""
    var nombre: String = ""
    var banco: String = ""
    var saldo: Float = 0.0f

    fun obtenerSaldo(): Float {
        return saldo
    }

    fun retirarDinero(cantidad: Float): Boolean {
        if (cantidad <= saldo) {
            saldo -= cantidad
            return true
        }
        return false
    }

    fun hacerDeposito(cantidad: Float) {
        saldo += cantidad
    }
}