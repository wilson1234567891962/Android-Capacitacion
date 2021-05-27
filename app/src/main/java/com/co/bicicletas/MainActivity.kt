package com.co.bicicletas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.co.bicicletas.model.entities.LoginDTO
import com.co.bicicletas.viewmodel.LoginViewModel


class MainActivity() : AppCompatActivity() {
    lateinit var textUser:EditText
    lateinit var textPass:EditText
    lateinit var buttonIngresar:Button
    lateinit var TextForget:TextView

    private lateinit var loginViewModel:LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         textUser = findViewById(R.id.user) as EditText
         textPass = findViewById(R.id.password) as EditText
         buttonIngresar = findViewById(R.id.ingresar) as Button
         TextForget= findViewById(R.id.forget) as TextView

        loginViewModel=ViewModelProvider(this).get(LoginViewModel::class.java)

        buttonIngresar.setOnClickListener(::login)
        TextForget.setOnClickListener(::resetPass);


    }

        fun resetPass(p: View?) {
            val myIntent = Intent(this, forgetPass::class.java)
            this.startActivity(myIntent)

        }

fun login(p: View?){
  Toast.makeText(
        this, "${textUser.text} ${textPass.text}" as String?,

        Toast.LENGTH_LONG
    ).show()

    loginViewModel.getLogin(LoginDTO(textPass.text.toString(),textUser.text.toString()))
    ViewModelObserver()

}
    fun ViewModelObserver(){
        loginViewModel.loginResponse.observe(this) { login ->
            login.let {
                Log.d("Login","sss")
            }
        }
    }

}



