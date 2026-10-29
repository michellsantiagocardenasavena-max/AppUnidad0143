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

class CuentaBancoActivity : AppCompatActivity() {

    private lateinit var lblNombre: TextView

    private lateinit var txtNumCuenta: EditText

    private lateinit var txtNombre: EditText

    private lateinit var txtBanco: EditText

    private lateinit var txtSaldo: EditText

    private lateinit var spnMovimientos: Spinner

    private lateinit var txtNuevoSaldo: TextView

    private lateinit var txtCantidad: EditText

    private lateinit var btnRegistrarCuenta: Button

    private lateinit var btnAplicarMovimiento: Button

    private var pos: Int = 0

    private var cuenta:
            CuentaBanco =
        CuentaBanco()

    private var cuentaRegistrada:
            Boolean =
        false

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(
            savedInstanceState
        )

        enableEdgeToEdge()

        setContentView(
            R.layout.activity_cuentabanco
        )

        iniciarComponentes()

        eventosClick()

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->

            val systemBars =
                insets.getInsets(
                    WindowInsetsCompat.Type.systemBars()
                )

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

        lblNombre =
            findViewById(
                R.id.lblNombre
            )

        txtNumCuenta =
            findViewById(
                R.id.txtNumCuenta
            )

        txtNombre =
            findViewById(
                R.id.txtNombre
            )

        txtBanco =
            findViewById(
                R.id.txtBanco
            )

        txtSaldo =
            findViewById(
                R.id.txtSaldo
            )

        spnMovimientos =
            findViewById(
                R.id.spnMovimientos
            )

        txtNuevoSaldo =
            findViewById(
                R.id.txtNuevoSaldo
            )

        txtCantidad =
            findViewById(
                R.id.txtCantidad
            )

        btnRegistrarCuenta =
            findViewById(
                R.id.btnRegistrarCuenta
            )

        btnAplicarMovimiento =
            findViewById(
                R.id.btnAplicarMovimiento
            )

        val usuario =
            intent.getStringExtra(
                "usuario"
            )

        lblNombre.text =
            "Usuario : " + usuario

        val items =
            resources.getStringArray(
                R.array.movimientos
            )

        val adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                items
            )

        spnMovimientos.adapter =
            adapter
    }

    fun eventosClick() {

        spnMovimientos.onItemSelectedListener =
            object :
                AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    pos =
                        position
                }

                override fun onNothingSelected(
                    parent: AdapterView<*>?
                ) {

                }
            }

        btnRegistrarCuenta.setOnClickListener {

            if (
                txtNumCuenta.text.toString().contentEquals("") ||
                txtNombre.text.toString().contentEquals("") ||
                txtBanco.text.toString().contentEquals("") ||
                txtSaldo.text.toString().contentEquals("")
            ) {

                Toast.makeText(
                    this,
                    R.string.strFaltaCuenta,
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            cuenta.numCuenta =
                txtNumCuenta.text.toString()

            cuenta.nombre =
                txtNombre.text.toString()

            cuenta.banco =
                txtBanco.text.toString()

            cuenta.saldo =
                txtSaldo.text.toString().toFloat()

            cuentaRegistrada =
                true

            txtNuevoSaldo.text =
                cuenta.obtenerSaldo().toString()

            Toast.makeText(
                this,
                "Cuenta registrada correctamente",
                Toast.LENGTH_SHORT
            ).show()
        }

        btnAplicarMovimiento.setOnClickListener {

            if (
                !cuentaRegistrada
            ) {

                Toast.makeText(
                    this,
                    R.string.strCuentaNoRegistrada,
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (
                pos != 0 &&
                txtCantidad.text.toString().contentEquals("")
            ) {

                Toast.makeText(
                    this,
                    R.string.strFaltaMovimiento,
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            when (pos) {

                0 -> {

                    txtNuevoSaldo.text =
                        cuenta.obtenerSaldo().toString()
                }

                1 -> {

                    val cantidad: Float =
                        txtCantidad.text.toString().toFloat()

                    if (
                        cuenta.retirarDinero(
                            cantidad
                        )
                    ) {

                        txtNuevoSaldo.text =
                            cuenta.obtenerSaldo().toString()

                    } else {

                        Toast.makeText(
                            this,
                            R.string.strSaldoInsuficiente,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                2 -> {

                    val cantidad: Float =
                        txtCantidad.text.toString().toFloat()

                    cuenta.hacerDeposito(
                        cantidad
                    )

                    txtNuevoSaldo.text =
                        cuenta.obtenerSaldo().toString()
                }
            }
        }
    }
}