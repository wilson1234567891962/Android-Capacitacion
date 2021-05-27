package com.co.bicicletas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.co.bicicletas.model.entities.BodyLogin
import com.co.bicicletas.model.entities.BodyRemember
import com.co.bicicletas.viewmodel.LoginViewModel
import com.co.bicicletas.viewmodel.RememberViewModel

class ForgotPass : AppCompatActivity() {

    lateinit var backLink : TextView
    lateinit var rememberViewModel : RememberViewModel
    lateinit var buttonRemember: Button
    lateinit var email : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)

        email = findViewById(R.id.userolvido) as EditText
        backLink = findViewById(R.id.link1olvido) as TextView
        buttonRemember = findViewById(R.id.buttonolvido) as Button

        rememberViewModel = ViewModelProvider(this).get(RememberViewModel::class.java)

        backLink.setOnClickListener(::backMain)

        buttonRemember.setOnClickListener(::remember)




    }
    fun remember(view:View?){
        rememberViewModel.remember(BodyRemember(email.text.toString()))
        getViewModelObserver()

    }
    fun backMain(view:View?){
        val myIntent = Intent(this, MainActivity::class.java)
        this.startActivity(myIntent)

    }

    fun getViewModelObserver() {
        rememberViewModel.rememberResponse.observe(this) { loginResponse ->
            loginResponse.let {
                Log.d("Respuesta ", it.message)
                Toast.makeText(this, it.message, Toast.LENGTH_LONG)
            }
        }

    }
}