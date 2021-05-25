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



        buttonAcc.setOnClickListener(::resetPass);

    }

fun resetPass(p : View ) {
    val myIntent = Intent(this, frogotPass::class.java)


    this.startActivity(myIntent)

}


}