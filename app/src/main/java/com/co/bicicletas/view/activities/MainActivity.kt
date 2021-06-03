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
import com.co.bicicletas.utils.extensiones.hideLoader
import com.co.bicicletas.utils.extensiones.showLoader
import com.co.bicicletas.viewModel.LoginViewModel
import com.co.bicicletas.viewModel.LoginViewModelFactory


class MainActivity : AppCompatActivity() {

    lateinit var etUser : EditText
    lateinit var etPass : EditText
    lateinit var forgotP : TextView
    lateinit var login : Button
    lateinit var loginVM: LoginViewModel
    lateinit var checkBox: CheckBox

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

        loginVM=ViewModelProvider(this).get(LoginViewModel::class.java)

        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    Toast.makeText(this,"Activado",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"Desactivado",Toast.LENGTH_LONG).show()
                }
        }


        }




    fun reset(p: View){
        val myIntent = Intent(this, forgotPass::class.java)
        myIntent.putExtra("key", "HOla") //Optional parameters

        this.startActivity(myIntent)
    }

    fun login(p:View){
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

}