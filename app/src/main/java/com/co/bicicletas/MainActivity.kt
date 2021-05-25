package com.co.bicicletas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var strUsuario : EditText
    lateinit var strPass : EditText
    lateinit var btnIngresar : Button
    lateinit var TvOlvido : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        strUsuario = findViewById(R.id.txtUser) as EditText
        strPass = findViewById(R.id.txtPass) as EditText
        btnIngresar = findViewById(R.id.btIngresar) as Button
        TvOlvido = findViewById(R.id.lblolvido) as TextView

    }
}