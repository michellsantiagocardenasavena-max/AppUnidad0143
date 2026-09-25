package com.example.appunidad014_3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {

    private lateinit var crvHola: CardView
    private lateinit var crvImc: CardView
    private lateinit var crvGrados: CardView
    private lateinit var crvMoneda: CardView
    private lateinit var crvCotizacion: CardView
    private lateinit var crvSalir: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        iniciarComponentes()
        eventosClick()

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->

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

    private fun iniciarComponentes() {

        crvHola = findViewById(R.id.crvHola)
        crvImc = findViewById(R.id.crvImc)
        crvGrados = findViewById(R.id.crvGrados)
        crvMoneda = findViewById(R.id.crvMoneda)
        crvCotizacion = findViewById(R.id.crvCotizacion)
        crvSalir = findViewById(R.id.crvSalir)
    }

    private fun eventosClick() {

        crvHola.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        crvImc.setOnClickListener {
            val intent = Intent(this, ImcActivity2::class.java)
            startActivity(intent)
        }

        crvMoneda.setOnClickListener {
            val intent = Intent(this, MonedasActivity::class.java)
            startActivity(intent)
        }
        crvGrados.setOnClickListener {
            val intent = Intent(this, GradosActivity::class.java)
            startActivity(intent)
        }
        crvCotizacion.setOnClickListener {
            val intent = Intent(this, ClienteActivity::class.java)
            startActivity(intent)
        }

        crvSalir.setOnClickListener {

            val builder = AlertDialog.Builder(this)

            builder.setTitle("App Unidad 01")
            builder.setMessage("¿Desea salir de la app?")

            builder.setPositiveButton("Aceptar") { _, _ ->
                finish()
            }

            builder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()

                Toast.makeText(
                    this,
                    "Continuamos",
                    Toast.LENGTH_SHORT
                ).show()
            }

            builder.show()
        }
    }
}