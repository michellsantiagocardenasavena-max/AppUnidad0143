package com.example.appunidad014_3

import android.app.AlertDialog
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

class MonedasActivity : AppCompatActivity() {

    private lateinit var txtCantidad: EditText
    private lateinit var spnMonedas: Spinner
    private lateinit var txtResultado: TextView

    private lateinit var btnConvertir: Button
    private lateinit var btnCerrar: Button
    private lateinit var btnLimpiar: Button
    private var pos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_monedas)

        iniciarComponentes()
        evento()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarComponentes() {

        txtCantidad = findViewById(R.id.txtCantidad)
        spnMonedas = findViewById(R.id.spnMonedas)
        txtResultado = findViewById(R.id.txtResultado)
        btnConvertir = findViewById(R.id.btnConvertir)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnSalir)

        val items = resources.getStringArray(R.array.strMonedas)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            items
        )

        spnMonedas.adapter = adapter
    }

    fun evento() {

        spnMonedas.onItemSelectedListener =
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

        btnConvertir.setOnClickListener {

            if (
                txtCantidad.text.toString().contentEquals("")
                || txtCantidad.text.toString().toFloat() <= 0
            ) {
                Toast.makeText(
                    this,
                    "Falto capturar informacion",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val dolarA: Float = getString(R.string.strDolarA).toFloat()
            val dolarC: Float = getString(R.string.strDolarC).toFloat()
            val euro: Float = getString(R.string.strEuro).toFloat()
            val libra: Float = getString(R.string.strLibra).toFloat()

            val cantidad: Float = txtCantidad.text.toString().toFloat()

            val resultado = when (pos) {
                0 -> cantidad / dolarA
                1 -> cantidad / dolarC
                2 -> cantidad / euro
                3 -> cantidad / libra
                else -> 0f
            }

            txtResultado.text = String.format("%.2f", resultado)
        }

        btnLimpiar.setOnClickListener {
            txtCantidad.setText("")
            txtResultado.text = ""
            spnMonedas.setSelection(0)
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