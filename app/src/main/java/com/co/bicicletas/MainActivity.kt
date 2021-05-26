package com.co.bicicletas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity() : AppCompatActivity() {
    lateinit var textUser:EditText
    lateinit var textPass:EditText
    lateinit var buttonIngresar:Button
    lateinit var TextForget:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         textUser = findViewById(R.id.user) as EditText
         textPass = findViewById(R.id.password) as EditText
         buttonIngresar = findViewById(R.id.ingresar) as Button
         TextForget= findViewById(R.id.forget) as TextView


        buttonIngresar.setOnClickListener(::showCredentials)
        TextForget.setOnClickListener(::resetPass);


    }

        fun resetPass(p: View?) {
            val myIntent = Intent(this, forgetPass::class.java)
            this.startActivity(myIntent)

        }

fun showCredentials(p: View?){
    Toast.makeText(
        this, "${textUser.text} ${textPass.text}" as String?,

        Toast.LENGTH_LONG
    ).show()
}

}



