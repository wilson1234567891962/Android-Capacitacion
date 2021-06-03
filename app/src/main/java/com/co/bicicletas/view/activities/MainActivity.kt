package com.co.bicicletas.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.co.bicicletas.R
import com.co.bicicletas.aplication.bicicletas.BicicletasApplication
import com.co.bicicletas.model.entities.LoginDTO
import com.co.bicicletas.utils.extensions.hideLoader
import com.co.bicicletas.utils.extensions.showLoader
import com.co.bicicletas.viewmodel.LoginViewModel
import com.co.bicicletas.viewmodel.LoginViewModelFactory


class MainActivity() : AppCompatActivity() {
    lateinit var textUser:EditText
    lateinit var textPass:EditText
    lateinit var buttonIngresar:Button
    lateinit var TextForget:TextView
    lateinit var checkboxEvt:CheckBox

    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory((this.application as BicicletasApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         textUser = findViewById(R.id.user) as EditText
         textPass = findViewById(R.id.password) as EditText
         buttonIngresar = findViewById(R.id.ingresar) as Button
         TextForget= findViewById(R.id.forget) as TextView
         checkboxEvt = findViewById(R.id.checkData) as CheckBox

        buttonIngresar.setOnClickListener(::login)
        TextForget.setOnClickListener(::resetPass);
        checkboxEvt.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                Toast.makeText(
                    this, "Recordar" as String?,
                    Toast.LENGTH_LONG
                ).show()
            }else{
                Toast.makeText(
                    this, "No Recordar" as String?,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    fun resetPass(p: View?) {
        val myIntent = Intent(this, forgetPass::class.java)
        this.startActivity(myIntent)


    }

fun login(p: View?){
   /* Toast.makeText(
        this, "${textUser.text} ${textPass.text}" as String?,

        Toast.LENGTH_LONG
    ).show()*/
    this.showLoader()
    loginViewModel.getLogin(LoginDTO(textPass.text.toString(),textUser.text.toString()))
    ViewModelObserver()

}
    fun ViewModelObserver(){
        loginViewModel.loginResponse.observe(this) { login ->
            login.let {
                this.hideLoader()
                val myIntent = Intent(this, HomeActivity::class.java)
                this.startActivity(myIntent)
            }
        }

//        loginViewModel.loadRandomDish.observe(this, Observer { loader ->
//            loader?.let {
//                // Show the progress dialog if the SwipeRefreshLayout is not visible and hide when the usage is completed.
//                if (loader) {
//                    this.showLoader()
//                } else {
//                    this.hideLoader()
//                }
//            }
//        })
   }

}



