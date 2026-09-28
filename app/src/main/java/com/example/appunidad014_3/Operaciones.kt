package com.example.appunidad014_3

import java.io.Serializable

class Operaciones : Serializable {

    var num1: Float = 0.0f
    var num2: Float = 0.0f

    fun suma(): Float {
        return num1 + num2
    }

    fun resta(): Float {
        return num1 - num2
    }

    fun multiplicacion(): Float {
        return num1 * num2
    }

    fun division(): Float {
        return num1 / num2
    }
}