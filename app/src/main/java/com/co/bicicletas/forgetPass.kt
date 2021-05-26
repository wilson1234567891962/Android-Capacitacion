package com.co.bicicletas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class forgetPass : AppCompatActivity() {
    lateinit var TextForget: TextView
    lateinit var TextHome: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pass)
        var TextHome= findViewById(R.id.home) as TextView

        TextHome.setOnClickListener(::backHome);

    }
    fun backHome(p: View?) {
        val myIntent = Intent(this, MainActivity::class.java)
        this.startActivity(myIntent)

    }

}