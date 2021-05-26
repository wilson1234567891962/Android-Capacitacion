package com.co.bicicletas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var etUser : EditText
    lateinit var etPass : EditText
    lateinit var forgotP : TextView
    lateinit var login : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUser=findViewById(R.id.etUser) as EditText
        etPass=findViewById(R.id.etPass) as EditText

        forgotP=findViewById(R.id.textView3) as TextView

        login=findViewById(R.id.boton) as Button

        forgotP.setOnClickListener(::reset)

        login.setOnClickListener(::showData)



        }

    fun login(p: View){

    }

    fun reset(p: View){
        val myIntent = Intent(this, forgotPass::class.java)
        myIntent.putExtra("key", "HOla") //Optional parameters

        this.startActivity(myIntent)
    }

    fun showData(p:View){
        Toast.makeText(this,"Usuario: ${etUser.text} \n Contraseña: ${etPass.text}",Toast.LENGTH_LONG).show()
    }
}