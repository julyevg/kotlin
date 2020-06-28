package com.example.cursokotlin2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity


import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject


class Login : AppCompatActivity() {

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
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        val user : User? = createUser();

        if( user == null)  {
            Toast.makeText(this, "No se logró obtener el usuario", Toast.LENGTH_SHORT).show()
            return
        }

        ingresar.setOnClickListener { view ->

            Toast.makeText(this, "toy aqui", Toast.LENGTH_SHORT).show()

           if( user.users == usuariologin.text.toString() && user.password == passwordlogin.text.toString() )
           {
               val intento1 = Intent(this, Perfil_Usuario::class.java)
               intento1.putExtra(KEY_NAME, user.nombre)
               intento1.putExtra(KEY_LASTNAME, user.lastname)
               intento1.putExtra(KEY_DNI, user.dni)
               intento1.putExtra(KEY_ADDRESS, user.address)
               startActivity(intento1)
           }
           else
           {
               Toast.makeText(this, "User y Password Incorrecto", Toast.LENGTH_SHORT).show()
           }

        }

        registrar.setOnClickListener{ view ->
            val intento1 = Intent(this,  MainActivity::class.java)
            startActivity(intento1)
        }
    }
    private fun createUser() : User? {

        val mShared  = mSharedPreferences(this)
        val session = mShared.getKey("session") ?: return null

        val sessionObj = JSONObject(session)

        users = sessionObj.getString(KEY_USUARIO)
        password = sessionObj.getString(KEY_PASSWORD)
        nombre = sessionObj.getString(KEY_NAME)
        lastname = sessionObj.getString(KEY_LASTNAME)
        dni = sessionObj.getString(KEY_DNI)
        address = sessionObj.getString(KEY_ADDRESS)

        val user = User(
            users, password, dni, nombre, lastname, address
        )
        return user
    }

}
