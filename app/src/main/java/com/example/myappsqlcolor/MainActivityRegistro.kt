package com.example.myappsqlcolor

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main_registro.*
import kotlinx.android.synthetic.main.content_main_activity_registro.*

class MainActivityRegistro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_registro)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->

           if (etCorreo.text.isEmpty() || etNombreR.text.isEmpty() || etPwdR.text.isEmpty() || etnumero.text.isEmpty()){
               etCorreo.requestFocus()
           }
            else{
               val ema = etCorreo.text.toString()
               val nom = etNombreR.text.toString()
               val pwd = etPwdR.text.toString()
               val tel = etnumero.text.toString()
               val sentencia = "INSERT INTO Usuario(emailUsr, nomUsr, contUsr, telUsr) values ('$ema', '$nom', '$pwd', '$tel')"
               val admin= adminBD(this)
               if(admin.Ejecuta(sentencia)==1){

                   Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_SHORT).show()
                   val lista = Intent(this,MainActivity::class.java)
                   lista.putExtra(MainActivity.EXTRA_CORREO, ema)
                   lista.putExtra(MainActivity.EXTRA_PWD, pwd)
                   startActivity(lista)
               }
               else{
                   Toast.makeText(this, "Error: No se pudo registrar", Toast.LENGTH_LONG).show()
               }
           }
        }
    }
}
