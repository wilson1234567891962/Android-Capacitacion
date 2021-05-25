package com.co.bicicletas

import android.R.attr.data
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var editTextUsername : EditText
    lateinit var editTextPassword: EditText
    lateinit var submitBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextUsername = findViewById(R.id.username) as EditText
        editTextPassword = findViewById(R.id.password) as EditText
        submitBtn = findViewById(R.id.enviar) as Button

        submitBtn.setOnClickListener {
            Toast.makeText(
                this, "data.result" as String?,
                Toast.LENGTH_LONG
            ).show()

        }
}