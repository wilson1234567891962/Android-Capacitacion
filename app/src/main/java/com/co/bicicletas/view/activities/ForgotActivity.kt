package com.co.bicicletas.view.activities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.co.bicicletas.R

class ForgotActivity : AppCompatActivity() {

    lateinit var buttonreset: Button
    lateinit var textForgot: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frogot_pass)
        textForgot = findViewById(R.id.textView3) as TextView
        textForgot.setOnClickListener(::BackHome);
        //buttonreset = findViewById(R.id.button2) as Button
        //buttonreset.setOnClickListener(::BackHome);

    }

    fun BackHome(p : View) {
        //val myIntent = Intent(this, MainActivity::class.java)


        //this.startActivity(myIntent)
            this.onBackPressed()
    }

}