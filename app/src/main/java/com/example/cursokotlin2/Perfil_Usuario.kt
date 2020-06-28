package com.example.cursokotlin2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*


import kotlinx.android.synthetic.main.activity_perfil__usuario.*
import kotlinx.android.synthetic.main.content_perfil__usuario.*
import org.json.JSONObject

class Perfil_Usuario : AppCompatActivity() {

    companion object{
        const val KEY_USUARIO="user"
        const val KEY_PASSWORD="password"
        const val KEY_NAME = "name"
        const val KEY_LASTNAME="lastname"
        const val KEY_DNI = "dni"
        const val KEY_ADDRESS = "address"
    }
    var users : String? = null
    var nombre : String? = null
    var dni : String? = null
    var password: String? = null
    var lastname : String? = null
    var address : String? =null

    data class User(val users:String?, val password:String?, val dni:String?, val nombre:String?, val lastname:String?, val address:String? )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil__usuario)
        setSupportActionBar(toolbar)


        val users : User? = createUser()
        if( users == null)  {
            Toast.makeText(this, "No se logró obtener el usuario", Toast.LENGTH_SHORT).show()
            return
        }
        perfil_usuario_nombre.text = users.nombre
        perfil_apellido.text = users.lastname
        perfil_dni.text = users.dni
        perfil_direccion.text =users.address

        editar.setOnClickListener {view->

            val intento = Intent(this, MainActivity::class.java)
            intento.putExtra(Login.KEY_USUARIO, users.nombre)
            intento.putExtra(Login.KEY_PASSWORD, users.nombre)
            intento.putExtra(Login.KEY_NAME, users.nombre)
            intento.putExtra(Login.KEY_LASTNAME, users.lastname)
            intento.putExtra(Login.KEY_DNI, users.dni)
            intento.putExtra(Login.KEY_ADDRESS, users.address)
            startActivity(intento)
        }


    }

    private fun createUser() :User? {

        val mShared  = mSharedPreferences(this)
        val session = mShared.getKey("session") ?: return null
        val sessionObj = JSONObject(session)

        users = sessionObj.getString(Perfil_Usuario.KEY_USUARIO)
        password = sessionObj.getString(Perfil_Usuario.KEY_PASSWORD)
        nombre = sessionObj.getString(Perfil_Usuario.KEY_NAME)
        lastname = sessionObj.getString(Perfil_Usuario.KEY_LASTNAME)
        dni = sessionObj.getString(Perfil_Usuario.KEY_DNI)
        address = sessionObj.getString(Perfil_Usuario.KEY_ADDRESS)

        val users = Perfil_Usuario.User(
            users, password, dni, nombre, lastname, address
        )
        return users
    }
}
