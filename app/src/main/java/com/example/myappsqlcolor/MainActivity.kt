package com.example.myappsqlcolor

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var correo: String=""
    var pwd: String=""

    companion object{
        val EXTRA_CORREO = "extra_correo"
        val EXTRA_PWD = "extra_pwd"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //declara la intencion con la finalidad jalar lo que se recibe
        val recibe=intent
        if (recibe!= null && recibe.hasExtra(EXTRA_CORREO) && recibe.hasExtra(EXTRA_PWD)){

            correo=recibe.getStringExtra(EXTRA_CORREO)
            pwd= recibe.getStringExtra(EXTRA_PWD)
            Toast.makeText(this, "Datos Recibidos", Toast.LENGTH_LONG).show()

        }
        else{

            val admin = adminBD(this)
            val tupla = admin.Consulta("select emailUsr, contUsr from Usuario")
            if(tupla!!.moveToFirst()){
                correo = tupla.getString(0)
                pwd = tupla.getString(1)
                Toast.makeText(this, "Oktl $correo", Toast.LENGTH_LONG).show();
            }
            else{
                val registro= Intent(this, MainActivityRegistro::class.java)
                startActivity(registro)
            }
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
