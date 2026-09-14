package com.example.appunidad014_3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var txtSalida: TextView
    private lateinit var txtNombre: EditText
    private lateinit var btnClick: Button
    private lateinit var btnCerrar: Button
    private lateinit var btnLimpiar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        iniciarComponentes()
        eventosClick()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarComponentes() {
        // relacionar los objetos con su loyout mediaante
        txtSalida = findViewById(R.id.txtSalida)
        txtNombre = findViewById(R.id.txtNombre)
        btnClick = findViewById(R.id.btnCLick)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnSalir)


    }

    fun eventosClick() {
        btnClick.setOnClickListener {
            var strMensaje: String = ""
            //VALIDA
            if (txtNombre.text.toString().contentEquals("")) {
                Toast.makeText(this, "Falto captirar Nombre", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            strMensaje = "! hola !" + txtNombre.text.toString() + "¿como estas?"
            txtSalida.text = strMensaje.toString()
        }

        btnLimpiar.setOnClickListener {
            txtSalida.text = ""
            txtNombre.setText("")
        }
        btnCerrar.setOnClickListener {
            this.finish()
        }

    }
}