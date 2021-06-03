package com.co.bicicletas.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.co.bicicletas.application.BicicletasApplications
import com.co.bicicletas.view.activities.R
import com.co.bicicletas.model.entities.LoginDTO
import com.co.bicicletas.model.entities.database.Login
import com.co.bicicletas.utils.extensiones.hideLoader
import com.co.bicicletas.utils.extensiones.showLoader
import com.co.bicicletas.viewModel.LoginViewModel
import com.co.bicicletas.viewModel.LoginViewModelFactory


class MainActivity : AppCompatActivity() {

    lateinit var etUser : EditText
    lateinit var etPass : EditText
    lateinit var forgotP : TextView
    lateinit var login : Button

    lateinit var checkBox: CheckBox
    var loginDatabase = Login(-1,"","", false)


    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory((this.application as BicicletasApplications).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUser=findViewById(R.id.etUser) as EditText
        etPass=findViewById(R.id.etPass) as EditText

        forgotP=findViewById(R.id.textView3) as TextView

        login=findViewById(R.id.boton) as Button

        checkBox= findViewById(R.id.checkBox)

        forgotP.setOnClickListener(::reset)

        login.setOnClickListener(::login)
        this.getUserById()
        }




    fun reset(p: View){
        val myIntent = Intent(this, forgotPass::class.java)
        myIntent.putExtra("key", "HOla") //Optional parameters

        this.startActivity(myIntent)
    }

    fun login(p:View){
        //Toast.makeText(this,"Usuario: ${etUser.text} \n Contraseña: ${etPass.text}",Toast.LENGTH_LONG).show()
        this.showLoader()
        loginViewModel.getLogin(LoginDTO(password =etPass.text.toString() ,user = etUser.text.toString()))
        getViewModelObserver()
    }

    fun getViewModelObserver(){
        loginViewModel.loginResponse.observe(this){
        login -> login.let {
            Toast.makeText(applicationContext, it.data.token, Toast.LENGTH_SHORT).show()
            this.hideLoader()
            val myIntent = Intent(this, HomeActivity::class.java)
            this.startActivity(myIntent)
        }
}
  /*      loginVM.loadRandomDish.observe(this, Observer { loader ->
            loader?.let {
                // Show the progress dialog if the SwipeRefreshLayout is not visible and hide when the usage is completed.
                if (loader) {
                    this.showLoader()
                } else {
                    this.hideLoader()
                }
            }
        })
*/
    }


    fun getUserById(){
        loginViewModel.getUserById.observe(this){login ->
            login.let{
                if(login.isNotEmpty()){
                    val userFilter=login.first()
                    checkBox.isChecked = userFilter.state
                    if(userFilter.state) {
                        (this.etUser as TextView).text = userFilter.mail
                        (this.etPass as TextView).text = userFilter.passw
                    }
                    this.loginDatabase = userFilter

                }
                this.checkBoxListener()
            }

        }
    }
    fun checkBoxListener(){
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            this.insertUserWhenHeChecksTheCheckBox(isChecked)
        }
    }

    private fun insertUserWhenHeChecksTheCheckBox(state: Boolean) {
        if(loginDatabase.id !== -1) {
            loginDatabase.passw = etPass.text.toString()
            loginDatabase.mail = etUser.text.toString()
            loginDatabase.state = state
            loginDatabase.id = 1
            loginViewModel.updateUser(loginDatabase)
        } else {
            loginDatabase.state = state
            loginDatabase.mail = etUser.text.toString()
            loginDatabase.state = state
            loginDatabase.id = 1
            loginViewModel.insertUser(loginDatabase)
        }
    }

}