package com.co.bicicletas.view.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.co.bicicletas.R
import com.co.bicicletas.model.entities.LoginDTO
import com.co.bicicletas.viewmodel.LoginViewModel
import com.co.bicicletas.viewmodel.LoginViewModelFactory
import com.co.bicicletas.application.BcicletasApplications
import hideLoader
import showLoader


class MainActivity : AppCompatActivity() {
    lateinit var usuario:EditText
    private lateinit var pass:EditText
    private lateinit var olvidoC:TextView
    private lateinit var buttonAcc: Button
    private lateinit var keepCheckBox : CheckBox

    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory((this.application as
                BcicletasApplications).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Log.d("PRUEBA","A1")
        setContentView(R.layout.activity_main)
        usuario = findViewById<EditText>(R.id.editText2)
        pass = findViewById<EditText>(R.id.editText)
        olvidoC = findViewById<TextView>(R.id.textView3)
        buttonAcc = findViewById<Button>(R.id.button1)
        keepCheckBox = findViewById<CheckBox>(R.id.keepcb)

        buttonAcc.setOnClickListener(::login)

        olvidoC.setOnClickListener(::resetPass)

        keepCheckBox.setOnCheckedChangeListener{buttonView, isChecked ->
            if (isChecked)
            {
                Toast.makeText(applicationContext, "Check Habilitado" as String,
                    Toast.LENGTH_LONG)

            }
            else
            {
                Toast.makeText(applicationContext, "Check DesHabilitado" as String,
                    Toast.LENGTH_LONG)
            }

        }




    }



    override fun onStart() {
        super.onStart()
        Log.d("PRUEBA","A2")
    }

    override fun onResume() {
        super.onResume()
        Log.d("PRUEBA","A3")

    }

    override fun onPause() {
        super.onPause()
        Log.d("PRUEBA","A4")
    }

    override fun onStop() {
        super.onStop()
        Log.d("PRUEBA","A5")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("PRUEBA","A6")

    }


private fun resetPass(p : View? ) {
    val myIntent = Intent(this, frogotPass::class.java)


    this.startActivity(myIntent)

}

    fun login(p : View?){
    this.showLoader()
        loginViewModel.getLogin(LoginDTO(pass.text.toString() , usuario.text.toString()))
        getViewModelObserver()

    }

    fun getViewModelObserver(){

        loginViewModel.loginResponse.observe(this) { login ->
            login.let {
                //Log.d("login","A2")
                //Toast.makeText(applicationContext, it.data.token, Toast.LENGTH_SHORT).show()
                this.hideLoader()
                val myIntent = Intent(this, HomeActivity::class.java)
                this.startActivity(myIntent)
            }
        }

        /*loginViewModel.loadRandomDish.observe(this, Observer { loader ->
            loader?.let {
                // Show the progress dialog if the SwipeRefreshLayout is not visible and hide when the usage is completed.
                if (loader) {
                    this.showLoader()
                } else {
                    this.hideLoader()
                }
            }
        })*/
    }






}