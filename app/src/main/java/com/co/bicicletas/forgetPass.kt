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
import com.co.bicicletas.model.entities.PassDTO
import com.co.bicicletas.viewmodel.LoginViewModel
import com.co.bicicletas.viewmodel.PassViewModel

class forgetPass : AppCompatActivity() {
    lateinit var SendMail: Button
    lateinit var TextHome: TextView
    lateinit var textMail: EditText

    private lateinit var passViewModel: PassViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pass)
        TextHome= findViewById(R.id.home) as TextView
        textMail=findViewById(R.id.email) as EditText
        SendMail = findViewById(R.id.enviarCorreo) as Button

        passViewModel= ViewModelProvider(this).get(PassViewModel::class.java)


        TextHome.setOnClickListener(::backHome);
        SendMail.setOnClickListener(::forgotPass);


    }


        fun forgotPass(p: View?) {

            passViewModel.getPass(PassDTO(textMail.text.toString()))
            ViewModelObserver()
        }

    fun ViewModelObserver(){
        passViewModel.PassResponse.observe(this) { pass ->
            pass.let {
                Toast.makeText(applicationContext, "Code: ${it.code} Mensaje: ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }






    fun backHome(p: View?) {
        val myIntent = Intent(this, MainActivity::class.java)
        this.startActivity(myIntent)

    }

}