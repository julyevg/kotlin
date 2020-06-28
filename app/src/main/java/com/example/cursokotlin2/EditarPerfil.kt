package com.example.cursokotlin2

import android.os.Bundle
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_editar_perfil.*
import kotlinx.android.synthetic.main.content_editar_perfil.*

class EditarPerfil : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)
        setSupportActionBar(toolbar)

        val user = findViewById<TextView>(R.id.usuario)
        user.setText(intent.getStringExtra(Login.KEY_USUARIO)).toString()
        val password = findViewById<TextView>(R.id.password)
        password.setText(intent.getStringExtra(Login.KEY_PASSWORD)).toString()
        val nombre = findViewById<TextView>(R.id.nombre)
        nombre.setText(intent.getStringExtra(Login.KEY_NAME)).toString()
        val apellido = findViewById<TextView>(R.id.apellido)
        apellido.setText(intent.getStringExtra(Login.KEY_LASTNAME)).toString()
        val dni = findViewById<TextView>(R.id.dni)
        dni.setText(intent.getStringExtra(Login.KEY_DNI)).toString()
        val direccion = findViewById<TextView>(R.id.dni)
        direccion.setText(intent.getStringExtra(Login.KEY_ADDRESS)).toString()

        guardar.setOnClickListener { view ->

        }
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}
