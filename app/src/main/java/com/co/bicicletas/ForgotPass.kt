package com.co.bicicletas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class ForgotPass : AppCompatActivity() {

    lateinit var backLink : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)

        backLink = findViewById(R.id.link1olvido) as TextView
        backLink.setOnClickListener(::backMain)




    }

    fun backMain(view:View?){
        val myIntent = Intent(this, MainActivity::class.java)
        this.startActivity(myIntent)
    }
}