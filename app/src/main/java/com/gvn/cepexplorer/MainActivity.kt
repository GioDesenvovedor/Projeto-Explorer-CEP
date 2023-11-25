package com.gvn.cepexplorer

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //esconder actionBar
        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#52057B")


        //Splash
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, BuscarCep::class.java)
            startActivity(intent)
            finish()
        }, 3000)//tempo de 3 segundos de exibição

    }
}