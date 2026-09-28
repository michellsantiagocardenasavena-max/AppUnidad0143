package com.example.appunidad014_3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PreActivity : AppCompatActivity() {

    private lateinit var txtNombre: EditText
    private lateinit var btnIngresar: Button
    private lateinit var btnCerrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_preexamen)

        iniciarComponentes()
        eventosClick()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())
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
        txtNombre = findViewById(R.id.txtNombre)
        btnIngresar = findViewById(R.id.btnIngresar)
        btnCerrar = findViewById(R.id.btnCerrar)
    }

    fun eventosClick() {
        btnIngresar.setOnClickListener {
            if (txtNombre.text.toString().contentEquals("")) {
                Toast.makeText(
                    this,
                    "Falto capturar el nombre",
                    Toast.LENGTH_SHORT
                ).show()
                txtNombre.requestFocus()
                return@setOnClickListener
            }

            val intent = Intent(this, CalculadoraActivity::class.java)
            intent.putExtra("nombre", txtNombre.text.toString())
            startActivity(intent)
        }

        btnCerrar.setOnClickListener {
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