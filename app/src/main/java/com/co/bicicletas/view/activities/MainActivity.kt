package com.co.bicicletas.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.co.bicicletas.application.BicicletasApplications
import com.co.bicicletas.model.entities.LoginDTO
import com.co.bicicletas.model.entities.database.Login
import com.co.bicicletas.utils.extensiones.hideLoader
import com.co.bicicletas.utils.extensiones.showLoader
import com.co.bicicletas.viewModel.LoginViewModel
import com.co.bicicletas.viewModel.LoginViewModelFactory


class MainActivity : AppCompatActivity() {

    lateinit var textUser : EditText
    lateinit var textPass : EditText
    lateinit var forgotP : TextView
    lateinit var login : Button
//    lateinit var loginVM: LoginViewModel
    lateinit var checkbox : CheckBox

    var loginDatabase = Login(-1,"","", false)

    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory((this.application as BicicletasApplications).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textUser=findViewById(R.id.etUser) as EditText
        textPass=findViewById(R.id.etPass) as EditText

        forgotP=findViewById(R.id.textView3) as TextView

        checkbox = findViewById(R.id.checkbox) as CheckBox

        login=findViewById(R.id.boton) as Button

        forgotP.setOnClickListener(::reset)

        login.setOnClickListener(::showData)

        this.getUserById()
//        loginVM = ViewModelProvider(this).get(LoginViewModel::class.java)

    }

    fun login(p: View){

    }

    fun reset (p: View){
        val myIntent = Intent(this, forgotPass::class.java)
        myIntent.putExtra("key", "HOla") //Optional parameters

        this.startActivity(myIntent)
    }

    fun showData (p:View){
        //Toast.makeText(this,"Usuario: ${etUser.text} \n Contraseña: ${etPass.text}",Toast.LENGTH_LONG).show()
        this.showLoader()
        loginViewModel.getLogin(LoginDTO(password =textPass.text.toString() ,user = textUser.text.toString()))
        getViewModelObserver()
    }

    fun getViewModelObserver() {
        loginViewModel.loginResponse.observe(this) { login ->
            login.let {
                Toast.makeText(applicationContext, it.data.token, Toast.LENGTH_SHORT).show()
                this.hideLoader()
            }
        }
    }

    fun getUserById(){
        loginViewModel.getUserById.observe(this) { login ->
            login.let {
                if (login.isNotEmpty()){
                    var userFilter = login.first()

                    checkbox.isChecked = userFilter.state
                    if(userFilter.state) {
                        (this.textUser as TextView).text = userFilter.email
                        (this.textPass as TextView).text = userFilter.password
                    }
                    this.loginDatabase = userFilter
                }
                this.checkBoxListener()
            }
        }
    }

    private fun insertUserWhenHeChecksTheCheckBox(state: Boolean) {
        if(loginDatabase.id !== -1) {
            loginDatabase.password = textPass.text.toString()
            loginDatabase.email = textUser.text.toString()
            loginDatabase.state = state
            loginDatabase.id = 1
            loginViewModel.updateUser(loginDatabase)
        } else {
            loginDatabase.state = state
            loginDatabase.email = textUser.text.toString()
            loginDatabase.state = state
            loginDatabase.id = 1
            loginViewModel.insertUser(loginDatabase)
        }
    }

    fun checkBoxListener() {
        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            insertUserWhenHeChecksTheCheckBox(isChecked)
//            if(isChecked){
//                Toast.makeText(applicationContext, "Recordar usuario", Toast.LENGTH_SHORT).show()
//            }else{
//                Toast.makeText(applicationContext, "No recordar", Toast.LENGTH_SHORT).show()
//            }
        }
    }
}