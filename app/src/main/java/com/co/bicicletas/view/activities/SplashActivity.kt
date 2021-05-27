package com.co.bicicletas.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.co.bicicletas.R
import com.co.bicicletas.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val splashBinding  = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)
    }
}