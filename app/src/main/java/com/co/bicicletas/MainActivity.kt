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
    lateinit var user: EditText
    lateinit var pass : EditText
    lateinit var forget : TextView
    lateinit var logButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        user = findViewById(R.id.user) as EditText
        pass = findViewById(R.id.pass) as EditText
        forget = findViewById(R.id.link1) as TextView
        logButton = findViewById(R.id.button) as Button
        forget.setOnClickListener(::resetPassword)

        logButton.setOnClickListener(::login)



    }
    fun resetPassword(view: View?){

        val myIntent = Intent(this, ForgotPass::class.java)
        this.startActivity(myIntent)
    }

    fun login(view: View?){
        val userS = user.text
        val password = pass.text
        val toPrint = "Usuario: ${userS},  Password: ${password}"
        Toast.makeText(this, toPrint, Toast.LENGTH_LONG).show()
    }
}