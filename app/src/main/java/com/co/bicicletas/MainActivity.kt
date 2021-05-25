package com.co.bicicletas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity() : AppCompatActivity() {
    lateinit var editTextHello:EditText
    lateinit var editTextPass:EditText
    lateinit var buttonIngresar:Button
    lateinit var TextForget:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var editTextHello = findViewById(R.id.user) as EditText
        var editTextPass = findViewById(R.id.password) as EditText
        var buttonIngresar = findViewById(R.id.ingresar) as Button
        var TextForget= findViewById(R.id.ingresar) as TextView


        buttonIngresar.setOnClickListener {
            Toast.makeText(
                this, "Click!" as String?,
                Toast.LENGTH_LONG
            ).show()
        }


}

}

