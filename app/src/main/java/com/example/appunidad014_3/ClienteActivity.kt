package com.example.appunidad014_3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appunidad014_3.databinding.ActivityClienteBinding

class ClienteActivity : AppCompatActivity() {
    private lateinit var b: ActivityClienteBinding
    private lateinit var txtNombre: EditText
    private lateinit var btnIngresar: Button
    private lateinit var btnSalir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        b = ActivityClienteBinding.inflate(layoutInflater)

        setContentView(b.root)

        eventosClic()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun eventosClic() {
        b.strtbtnIngresar.setOnClickListener {

            if (b.txtCliente.text.contentEquals("")) {
                Toast.makeText(
                    this,
                    "Falto capturar el nombre del cliente",
                    Toast.LENGTH_SHORT
                ).show()

                b.txtCliente.requestFocus()

                return@setOnClickListener
            }

            val intent = Intent(this, CotizacionActivity::class.java)

            intent.putExtra(
                "cliente",
                b.txtCliente.text.toString()
            )

            startActivity(intent)
        }

        b.strbtnRegresar.setOnClickListener {
            finish()
        }
    }
}