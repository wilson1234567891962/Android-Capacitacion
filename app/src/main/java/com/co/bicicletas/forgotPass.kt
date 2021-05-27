package com.co.bicicletas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.co.bicicletas.databinding.ActivityForgotPassBinding
import com.co.bicicletas.model.entities.LoginDTO
import com.co.bicicletas.model.entities.forgotPassRQ
import com.co.bicicletas.viewModel.LoginViewModel
import com.co.bicicletas.viewModel.forgotViewModel

class forgotPass : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityForgotPassBinding

    lateinit var etMail : EditText
    lateinit var boton : Button

    lateinit var forgotVM:forgotViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_forgot_pass)
        etMail=findViewById(R.id.etMail)

        var backLogin = findViewById(R.id.textView4) as TextView
        backLogin.setOnClickListener(::back)
        boton = findViewById(R.id.boton)
        boton.setOnClickListener(::forgot)


        forgotVM=
            ViewModelProvider(this).get(forgotViewModel::class.java)
    }

    fun back(p: View){
        val myIntent = Intent(this, MainActivity::class.java)
        this.startActivity(myIntent)
    }

    fun forgot(p:View){
        forgotVM.doForgot(forgotPassRQ(email = etMail.text.toString()))
        getViewModelObserver()
    }

    fun getViewModelObserver(){
        forgotVM.forgotResponse.observe(this){
                forget -> forget.let {
            Toast.makeText(applicationContext, "Codigo: ${it.code} \n Mensaje: ${it.message}", Toast.LENGTH_SHORT).show()
        }
        }

    }
}