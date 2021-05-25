package com.co.bicicletas

import android.R.attr.data
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var usuario:EditText
    lateinit var contraseña:EditText
    lateinit var olvidoC:TextView
    lateinit var buttonAcc: Button
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usuario = findViewById(R.id.editText2) as EditText
        contraseña = findViewById(R.id.editText) as EditText
        olvidoC = findViewById(R.id.textView3) as TextView
        buttonAcc = findViewById(R.id.button1) as Button
        buttonAcc.setOnClickListener {
            Toast.makeText(
                this, "data.result" as String?,
                Toast.LENGTH_LONG
            ).show()
        }
    }





}