package com.co.bicicletas.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.co.bicicletas.R
import com.co.bicicletas.aplication.bicicletas.BicicletasApplication
import com.co.bicicletas.model.entities.LoginDTO
import com.co.bicicletas.utils.extensions.hideLoader
import com.co.bicicletas.utils.extensions.showLoader
import com.co.bicicletas.viewmodel.LoginViewModel
import com.co.bicicletas.viewmodel.LoginViewModelFactory


class MainActivity() : AppCompatActivity() {
    lateinit var textUser: EditText
    lateinit var textPass: EditText
    lateinit var buttonIngresar: Button
    lateinit var TextForget: TextView
    lateinit var checkBox: CheckBox
    // private lateinit var loginViewModel: LoginViewModel

    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory((this.application as BicicletasApplication).Repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textUser = findViewById(R.id.user) as EditText
        textPass = findViewById(R.id.password) as EditText
        buttonIngresar = findViewById(R.id.ingresar) as Button
        TextForget = findViewById(R.id.forget) as TextView
        checkBox = findViewById(R.id.recordar) as CheckBox

        //loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        buttonIngresar.setOnClickListener(::login)
        TextForget.setOnClickListener(::resetPass);

        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                Toast.makeText(
                    this, "Habilitado" as String?,
                    Toast.LENGTH_LONG
                ).show()
            }
            else{
                Toast.makeText(
                    this, "Deshabilitado" as String?,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    fun resetPass(p: View?) {
        val myIntent = Intent(this, forgetPass::class.java)
        this.startActivity(myIntent)


    }

    fun login(p: View?) {
        /* Toast.makeText(
             this, "${textUser.text} ${textPass.text}" as String?,
             Toast.LENGTH_LONG
         ).show()*/
        this.showLoader()
        loginViewModel.getLogin(LoginDTO(textPass.text.toString(), textUser.text.toString()))
        ViewModelObserver()

    }

    fun ViewModelObserver() {
        loginViewModel.loginResponse.observe(this) { login ->
            login.let {
                //Toast.makeText(applicationContext, it.data.token, Toast.LENGTH_SHORT).show()
                this.hideLoader()
                val myIntent = Intent(this, HomeActivity::class.java)
                this.startActivity(myIntent)
            }
        }

    }


}
