package com.co.bicicletas.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.co.bicicletas.view.activities.R
import com.co.bicicletas.model.entities.LoginDTO
import com.co.bicicletas.utils.extensiones.hideLoader
import com.co.bicicletas.utils.extensiones.showLoader
import com.co.bicicletas.viewModel.LoginViewModel


class MainActivity : AppCompatActivity() {

    lateinit var etUser : EditText
    lateinit var etPass : EditText
    lateinit var forgotP : TextView
    lateinit var login : Button
    lateinit var loginVM: LoginViewModel
    lateinit var checkbox : CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUser=findViewById(R.id.etUser) as EditText
        etPass=findViewById(R.id.etPass) as EditText

        forgotP=findViewById(R.id.textView3) as TextView

        checkbox = findViewById(R.id.checkbox) as CheckBox

        login=findViewById(R.id.boton) as Button

        forgotP.setOnClickListener(::reset)

        login.setOnClickListener(::showData)

        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                Toast.makeText(applicationContext, "CUalquier", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "Otro mensaje", Toast.LENGTH_SHORT).show()

            }
        }

        loginVM=
            ViewModelProvider(this).get(LoginViewModel::class.java)

        }

    fun login(p: View){

    }

    fun reset(p: View){
        val myIntent = Intent(this, forgotPass::class.java)
        myIntent.putExtra("key", "HOla") //Optional parameters

        this.startActivity(myIntent)
    }

    fun showData(p:View){
        //Toast.makeText(this,"Usuario: ${etUser.text} \n Contraseña: ${etPass.text}",Toast.LENGTH_LONG).show()
        this.showLoader()
        loginVM.getLogin(LoginDTO(password =etPass.text.toString() ,user = etUser.text.toString()))
        getViewModelObserver()
    }

    fun getViewModelObserver(){
        loginVM.loginResponse.observe(this){
        login -> login.let {
            Toast.makeText(applicationContext, it.data.token, Toast.LENGTH_SHORT).show()
            this.hideLoader()
        }
}

    }
}