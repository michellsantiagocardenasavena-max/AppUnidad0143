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

class BancoActivity : AppCompatActivity() {

    private lateinit var txtUser: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnIngresar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_banco)

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

    fun iniciarComponentes() {

        txtUser =
            findViewById(R.id.txtUser)

        txtPassword =
            findViewById(R.id.txtPassword)

        btnIngresar =
            findViewById(R.id.btnIngresar)
    }

    fun eventosClick() {

        btnIngresar.setOnClickListener {

            if (
                txtUser.text.toString().contentEquals("") ||
                txtPassword.text.toString().contentEquals("")
            ) {

                Toast.makeText(
                    this,
                    R.string.strFaltaUsuario,
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val user =
                getString(R.string.user)

            val pass =
                getString(R.string.pass)

            if (
                !txtUser.text.toString().contentEquals(user) ||
                !txtPassword.text.toString().contentEquals(pass)
            ) {

                Toast.makeText(
                    this,
                    R.string.strUsuarioIncorrecto,
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val intent =
                Intent(
                    this,
                    CuentaBancoActivity::class.java
                )

            intent.putExtra(
                "usuario",
                txtUser.text.toString()
            )

            startActivity(intent)
        }
    }
}