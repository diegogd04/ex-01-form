package com.example.proyectoprueba.features

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.proyectoprueba.R
import com.example.proyectoprueba.features.ex01.presentation.Ex01FormActivity
import com.example.proyectoprueba.features.ex02.MainActivityEx02
import com.example.proyectoprueba.features.ex03.Ex03GlideActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView(){
        findViewById<Button>(R.id.action_ex01_form).setOnClickListener{
            startActivity(Intent(this, Ex01FormActivity::class.java))
        }
        findViewById<Button>(R.id.action_ex02).setOnClickListener{
            startActivity(Intent(this, MainActivityEx02::class.java))
        }
        findViewById<Button>(R.id.action_ex03).setOnClickListener{
            startActivity(Intent(this, Ex03GlideActivity::class.java))
        }
    }
}