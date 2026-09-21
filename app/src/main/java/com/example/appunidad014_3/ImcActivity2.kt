package com.example.appunidad014_3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ImcActivity2 : AppCompatActivity() {

    private lateinit var txtAltura: EditText
    private lateinit var txtPeso: EditText
    private lateinit var LblResultado: TextView
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button
    private lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc2)

        iniciarComponentes()
        eventoClick()

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

        txtAltura = findViewById(R.id.txtAltura)
        txtPeso = findViewById(R.id.txtPeso)
        LblResultado = findViewById(R.id.txtResultado)

        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnCerrar)

        img = findViewById<ImageView>(R.id.image)
    }

    fun eventoClick() {

        // Validar que tenga información el peso y altura
        // Validar que los valores sean mayores a 0
        btnCalcular.setOnClickListener {

            if (
                txtAltura.text.toString().contentEquals("") ||
                txtPeso.text.toString().contentEquals("") ||
                txtPeso.text.toString().toFloat() <= 0 ||
                txtAltura.text.toString().toFloat() <= 0
            ) {

                Toast.makeText(
                    this,
                    "¡Faltó capturar información o valores, debe ser positivos!",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            var altura: Float = txtAltura.text.toString().toFloat()
            var peso: Float = txtPeso.text.toString().toFloat()

            var imc: Float = 0.0f

            imc = peso / (altura * altura)

            LblResultado.text = String.format("%.2f", imc)

            if (imc < 18.5)
                img.setImageResource(R.mipmap.bajopeso)

            if (imc >= 18.5 && imc < 24.9)
                img.setImageResource(R.mipmap.pesonormal)

            if (imc >= 25 && imc < 29.9)
                img.setImageResource(R.mipmap.sobrepeso)

            if (imc >= 30)
                img.setImageResource(R.mipmap.obesidad)
        }

        btnLimpiar.setOnClickListener {

            img.setImageResource(R.mipmap.categorias)

            txtAltura.setText("")
            txtPeso.setText("")

            LblResultado.text = getString(R.string.strResultado)
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