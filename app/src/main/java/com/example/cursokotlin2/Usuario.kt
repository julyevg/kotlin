package com.example.cursokotlin2

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONObject

class Usuario {


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

    constructor(context: Context) {
        this.users = users
        this.nombre = nombre
        this.dni = dni
        this.password = password
        this.lastname = lastname
        this.address = address
    }

    data class User(val users:String?, val password:String?, val dni:String?, val nombre:String?, val lastname:String?, val address:String? )

     fun createUser( sessionObj: JSONObject) : User? {

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