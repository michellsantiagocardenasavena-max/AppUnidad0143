package com.example.appunidad014_3

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CalculadoraActivity : AppCompatActivity() {

    private lateinit var txtNombre: TextView
    private lateinit var txtNum1: EditText
    private lateinit var txtNum2: EditText
    private lateinit var spnOperacion: Spinner
    private lateinit var txtResultado: TextView
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnRegresar: Button

    private var pos: Int = 0
    private var operaciones: Operaciones = Operaciones()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculadora)

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
        txtNum1 = findViewById(R.id.txtNum1)
        txtNum2 = findViewById(R.id.txtNum2)
        spnOperacion = findViewById(R.id.spnOperacion)
        txtResultado = findViewById(R.id.txtResultado)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnRegresar = findViewById(R.id.btnRegresar)

        val nombre = intent.getStringExtra("nombre") ?: ""
        txtNombre.text = nombre

        val items = resources.getStringArray(R.array.strOperaciones)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            items
        )
        spnOperacion.adapter = adapter
    }

    fun eventosClick() {
        spnOperacion.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    pos = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        btnCalcular.setOnClickListener {
            if (
                txtNum1.text.toString().contentEquals("") ||
                txtNum2.text.toString().contentEquals("")
            ) {
                Toast.makeText(
                    this,
                    "Faltó capturar información, todos los campos son requeridos",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            operaciones.num1 = txtNum1.text.toString().toFloat()
            operaciones.num2 = txtNum2.text.toString().toFloat()

            val resultado = when (pos) {
                0 -> operaciones.suma()
                1 -> operaciones.resta()
                2 -> operaciones.multiplicacion()
                3 -> operaciones.division()
                else -> 0f
            }

            txtResultado.text = String.format("%.2f", resultado)
        }

        btnLimpiar.setOnClickListener {
            txtNum1.setText("")
            txtNum2.setText("")
            txtResultado.text = "resultador"
            spnOperacion.setSelection(0)
        }

        btnRegresar.setOnClickListener {
            finish()
        }
    }
}