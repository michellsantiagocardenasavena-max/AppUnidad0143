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
import com.example.appunidad014_3.databinding.ActivityCotizacionBinding

class CotizacionActivity : AppCompatActivity() {
    private lateinit var b: ActivityCotizacionBinding
    private lateinit var txtCliente : TextView
    private lateinit var txtFolio : TextView
    private lateinit var txtDescripcion : EditText
    private lateinit var txtPorPaqI : EditText
    private lateinit var txtPrecio : EditText
    private lateinit var rbd12 : RadioButton
    private lateinit var rbd24 : RadioButton
    private lateinit var rbd36 : RadioButton
    private lateinit var rbd48 : RadioButton
    private lateinit var txtPagoIncial : TextView
    private lateinit var txtTotslFin : TextView
    private lateinit var txtPagoMensual : TextView
    private lateinit var btnCalcular : Button
    private lateinit var btnLimpiar : Button
    private lateinit var btnRegresar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        b = ActivityCotizacionBinding.inflate(layoutInflater)

        setContentView(b.root)
        iniciar()
        eventoclick()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciar(){
        val strCliente = intent.getStringExtra("cliente")
        b.txtCliente.text = strCliente.toString()

        val cotizacion = Cotizacion()
        b.txtFolio.text = cotizacion.generarFolio().toString()
    }

    fun eventoclick(){

        b.btnCalcular.setOnClickListener {

            if (
                b.txtDescripcion.text.toString().isEmpty()
                || b.txtPrecio.text.toString().isEmpty()
                || b.txtPorcentaje.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "falto capturar informacion", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val cotizacion = Cotizacion()

            cotizacion.descripcion = b.txtDescripcion.text.toString()
            cotizacion.porPagoInc = b.txtPorcentaje.text.toString().toFloat()
            cotizacion.precio = b.txtPrecio.text.toString().toFloat()

            if (b.rdb12.isChecked) cotizacion.plazos = 12
            if (b.rdb24.isChecked) cotizacion.plazos = 24
            if (b.rdb36.isChecked) cotizacion.plazos = 36
            if (b.rdb48.isChecked) cotizacion.plazos = 48

            b.txtPagoI.text =
                "Pago incial : $" + String.format("%.2f", cotizacion.calcularPagoInicial())

            b.txtTotalFin.text =
                "Total a financiar : $" + String.format("%.2f", cotizacion.calcularTotalFin())

            b.txtPagoMensual.text =
                "pago mensuaal : $" + String.format("%.2f", cotizacion.calcularPagoMensual())
        }

        b.btnLimpiar.setOnClickListener {

            val cotizacion = Cotizacion()

            b.txtFolio.text = cotizacion.generarFolio().toString()
            b.txtDescripcion.setText("")
            b.txtPrecio.setText("")
            b.txtPagoI.setText("")

            b.rdb12.isChecked = true

            b.txtPagoI.text = "Pago inicial"
            b.txtTotalFin.text = "Total a Fincanciar"
            b.txtPagoMensual.text = "Plazos"

            b.txtDescripcion.requestFocus()
        }

        b.btnSalir.setOnClickListener {

            val builder = AlertDialog.Builder(this)

            builder.setTitle("App Unidad 01")
            builder.setMessage("¿Desea salir de la app?")

            builder.setPositiveButton("Aceptar"){dialog, which ->
                finish()
            }

            builder.setNegativeButton("Cancelar"){dialog, which ->
                Toast.makeText(this, "Continuamos", Toast.LENGTH_SHORT).show()
            }

            builder.show()
        }
    }
}