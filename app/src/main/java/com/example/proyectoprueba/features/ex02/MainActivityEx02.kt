package com.example.proyectoprueba.features.ex02

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoprueba.R
import com.example.proyectoprueba.features.ex02.burguer.presentation.Ex02BurguerFormActivity

class MainActivityEx02 : AppCompatActivity(){
    override fun onCreate(savedInstaceState: Bundle?){
        super.onCreate(savedInstaceState)
        setContentView(R.layout.activity_ex02)
        setupView()
    }

    private fun setupView(){
        findViewById<Button>(R.id.action_ex02_burguer).setOnClickListener{
            startActivity(Intent(this, Ex02BurguerFormActivity::class.java))
        }
    }
}