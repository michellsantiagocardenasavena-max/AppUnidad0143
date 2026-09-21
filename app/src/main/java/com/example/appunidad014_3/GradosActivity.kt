package com.example.appunidad014_3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GradosActivity : AppCompatActivity() {

    private lateinit var txtCantidad: EditText
    private lateinit var rdbCel: RadioButton
    private lateinit var rdbFah: RadioButton
    private lateinit var txtRes: TextView
    private lateinit var btnCalcularG: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnSalir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_grados)

        iniciarComponentes()
        eventosClick()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }
    }

    fun iniciarComponentes() {
        txtCantidad = findViewById(R.id.txtCantidad)
        rdbCel = findViewById(R.id.rdCel)
        rdbFah = findViewById(R.id.rdFah)
        txtRes = findViewById(R.id.txtResultado)
        btnCalcularG = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnSalir = findViewById(R.id.btnSalir)
    }

    fun eventosClick() {

        btnCalcularG.setOnClickListener {

            if (txtCantidad.text.toString().contentEquals("")) {

                Toast.makeText(
                    this,
                    R.string.strMensajeGrados,
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            var Cantidad: Float =
                txtCantidad.text.toString().toFloat()

            if (rdbCel.isChecked) {

                var cel: Float = 0.0f

                cel = (Cantidad * 9 / 5) + 32

                txtRes.text =
                    cel.toString()

            } else {

                var fah: Float = 0.0f

                fah = (Cantidad - 32) * 5 / 9

                txtRes.text =
                    fah.toString()
            }
        }

        btnLimpiar.setOnClickListener {

            txtCantidad.setText("")
            txtRes.text = getString(R.string.strResultadoG)
            rdbCel.isChecked = true
        }

        btnSalir.setOnClickListener {

            val builder = AlertDialog.Builder(this)

            builder.setTitle("App Hola")
            builder.setMessage("¿Deseas salir de la app?")

            builder.setPositiveButton("Aceptar") { dialog, which ->
                finish()
            }

            builder.setNegativeButton("Cancelar") { dialog, which ->

                Toast.makeText(
                    this,
                    "Continua en la app",
                    Toast.LENGTH_SHORT
                ).show()
            }

            builder.show()
        }
    }
}