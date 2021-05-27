package com.co.bicicletas.view.activities
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.co.bicicletas.R
import com.co.bicicletas.model.entities.LoginDTO
import com.co.bicicletas.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {
    lateinit var usuario: EditText
    private lateinit var pass: EditText
    private lateinit var olvidoC: TextView
    private lateinit var buttonAcc: Button

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Log.d("PRUEBA", "A1")
        setContentView(R.layout.activity_main)
        usuario = findViewById<EditText>(R.id.editText2)
        pass = findViewById<EditText>(R.id.editText)
        olvidoC = findViewById<TextView>(R.id.textView3)
        buttonAcc = findViewById<Button>(R.id.button1)
        buttonAcc.setOnClickListener(::login)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        olvidoC.setOnClickListener(::resetPass);

    }


    override fun onStart() {
        super.onStart()
        Log.d("PRUEBA", "A2")
    }

    override fun onResume() {
        super.onResume()
        Log.d("PRUEBA", "A3")

    }

    override fun onPause() {
        super.onPause()
        Log.d("PRUEBA", "A4")
    }

    override fun onStop() {
        super.onStop()
        Log.d("PRUEBA", "A5")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("PRUEBA", "A6")

    }


    private fun resetPass(p: View?) {
        val myIntent = Intent(this, frogotPass::class.java)


        this.startActivity(myIntent)

    }

    private fun login(view: View?) {

        loginViewModel.getLogin(LoginDTO(pass.text.toString(), usuario.text.toString()))
        getViewModelObserver()
    }

    private fun getViewModelObserver() {
        loginViewModel.loginResponse.observe(this) { login ->
            login.let {
                Toast.makeText(applicationContext, it.data.token, Toast.LENGTH_SHORT).show()
            }
        }
    }
}