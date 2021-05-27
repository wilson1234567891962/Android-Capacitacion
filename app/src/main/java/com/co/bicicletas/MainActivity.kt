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
import androidx.lifecycle.ViewModelProvider
import com.co.bicicletas.model.entities.BodyLogin
import com.co.bicicletas.viewmodel.LoginViewModel
import retrofit2.http.Body


class MainActivity : AppCompatActivity() {
    lateinit var user: EditText
    lateinit var pass : EditText
    lateinit var forget : TextView
    lateinit var logButton : Button
    lateinit var loginViewModel : LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        user = findViewById(R.id.user) as EditText
        pass = findViewById(R.id.pass) as EditText
        forget = findViewById(R.id.link1) as TextView
        logButton = findViewById(R.id.button) as Button
        forget.setOnClickListener(::resetPassword)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        logButton.setOnClickListener(::login)



    }
    fun resetPassword(view: View?){

        val myIntent = Intent(this, ForgotPass::class.java)
        this.startActivity(myIntent)
    }

    fun login(view: View?){
        loginViewModel.login(BodyLogin(pass.text.toString(), user.text.toString()))
        getViewModelObserver()
    }

    fun getViewModelObserver(){
        loginViewModel.loginResponse.observe(this) {
            loginResponse ->
            loginResponse.let {
                Log.d("Token", it.data.token)
            }
        }

    }
}