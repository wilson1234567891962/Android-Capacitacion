package com.co.bicicletas

import android.R.attr.data
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var editTextHello:EditText
    lateinit var editTextPass:EditText
    lateinit var buttonAcc:Button
    lateinit var linkOlvido: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextHello = findViewById(R.id.editText) as EditText
        editTextPass = findViewById(R.id.editText2) as EditText
        buttonAcc = findViewById(R.id.buttonIng) as Button
        linkOlvido = findViewById(R.id.textOlvido) as TextView

        buttonAcc.setOnClickListener {
            Toast.makeText(
                this, "Mensaje Boton" as String?,
                Toast.LENGTH_LONG
            ).show()
        }
    }


}