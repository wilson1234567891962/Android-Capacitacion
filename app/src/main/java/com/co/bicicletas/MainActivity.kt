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
    private lateinit var pass:EditText
    private lateinit var olvidoC:TextView
    private lateinit var buttonAcc: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usuario = findViewById<EditText>(R.id.editText2)
        pass = findViewById<EditText>(R.id.editText)
        olvidoC = findViewById<TextView>(R.id.textView3)
        buttonAcc = findViewById<Button>(R.id.button1)

        buttonAcc.setOnClickListener(::showCredentials)


        olvidoC.setOnClickListener(::resetPass);

    }

private fun resetPass(p : View? ) {
    val myIntent = Intent(this, frogotPass::class.java)


    this.startActivity(myIntent)

}

    fun showCredentials(p : View?){
        Toast.makeText(
            this,  "${usuario.text} ${pass.text}" as String?,
            Toast.LENGTH_LONG
        ).show()
    }

}