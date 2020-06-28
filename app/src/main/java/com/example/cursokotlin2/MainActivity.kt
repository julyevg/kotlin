package com.example.cursokotlin2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Estado", "onCreate")

        val mShared  = mSharedPreferences(this)
        val session = mShared.getKey("session")
        val sessionObj = JSONObject(session)
        val users : Usuario.User? = Usuario(this).createUser(sessionObj)

        if( users != null)
        {

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
            val direccion = findViewById<TextView>(R.id.direccion)
            direccion.setText(intent.getStringExtra(Login.KEY_ADDRESS)).toString()

        }
        button.setOnClickListener{view ->
            if(validateData() )
            {
                val intento1 = Intent(this, Login::class.java)
                guardar(usuario.text.toString(), password.text.toString(), nombre.text.toString(),apellido.text.toString(), dni.text.toString(), direccion.text.toString())
                startActivity(intento1)
            }
            else
            {
                Toast.makeText(this, "No se logró obtener el usuario", Toast.LENGTH_SHORT).show()
            }
        }
    }
private fun validateData():Boolean {
    return (usuario.text.toString() != null && password.text.toString() != null && nombre.text.toString() != null && apellido.text.toString() != null
            && dni.text.toString() != null && direccion.text.toString() != null)
}

    private fun guardarJson(usuario:String, password:String, name:String, lastname:String, dni:String, address:String  ) {
        val json = JSONObject()
        json.put(Login.KEY_USUARIO, usuario)
        json.put(Login.KEY_PASSWORD, password)
        json.put(Login.KEY_NAME, name)
        json.put(Login.KEY_LASTNAME, lastname)
        json.put(Login.KEY_DNI, dni)
        json.put(Login.KEY_ADDRESS, address)
        val sf = mSharedPreferences(this)
        sf.put("session", json.toString())
        sf.save()
    }
    private fun guardar(usuario:String, password:String, name:String, lastname:String, dni:String, address:String  ) {

        guardarJson(usuario, password, name, lastname, dni, address)

        val sf = mSharedPreferences(this)
        sf.put(Login.KEY_USUARIO, usuario)
        sf.put(Login.KEY_PASSWORD, password)
        sf.put(Login.KEY_NAME, name)
        sf.put(Login.KEY_LASTNAME, lastname)
        sf.put(Login.KEY_DNI, dni)
        sf.put(Login.KEY_ADDRESS, address)
        sf.save()

    }

    override fun onStart() {
        super.onStart()
        Log.d("Estado", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Estado", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Estado", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Estado", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Estado", "onDestroy")
    }

}
