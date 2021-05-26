package com.co.bicicletas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.co.bicicletas.databinding.ActivityForgotPassBinding

class forgotPass : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityForgotPassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_forgot_pass)
        var backLogin = findViewById(R.id.textView4) as TextView
        backLogin.setOnClickListener(::back)

    }

    fun back(p: View){
        val myIntent = Intent(this, MainActivity::class.java)
        this.startActivity(myIntent)
    }
}