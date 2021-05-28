package com.co.bicicletas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.co.bicicletas.model.entities.ForgotDTO
import com.co.bicicletas.model.entities.LoginDTO
import com.co.bicicletas.viewmodel.ForgotViewModel
import com.co.bicicletas.viewmodel.LoginViewModel

class frogotPass : AppCompatActivity() {

    lateinit var buttonreset: Button
    lateinit var textForgot: TextView
    lateinit var editTextForgot: EditText
    private lateinit var forgotViewModel: ForgotViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frogot_pass)
        textForgot = findViewById(R.id.textView3) as TextView
        editTextForgot = findViewById(R.id.editText6) as EditText
        textForgot.setOnClickListener(::BackHome);

        buttonreset = findViewById(R.id.button12) as Button
        buttonreset.setOnClickListener(::forgot);
        forgotViewModel = ViewModelProvider(this).get(ForgotViewModel::class.java)


    }

    fun BackHome(p : View) {
        //val myIntent = Intent(this, MainActivity::class.java)


        //this.startActivity(myIntent)
            this.onBackPressed()
    }


    fun forgot(p : View?){

        forgotViewModel.getForgot(ForgotDTO(editTextForgot.text.toString()))
        getViewModelObserver()
    }

    fun getViewModelObserver(){

        forgotViewModel.forgotResponse.observe(this) { login ->
            login.let {
                //Log.d("login","A2")
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}