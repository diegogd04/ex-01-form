package com.example.proyectoprueba.features.ex03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectoprueba.R
import com.example.proyectoprueba.databinding.ActivityEx03GlideBinding

class Ex03GlideActivity : AppCompatActivity() {

    lateinit var binding: ActivityEx03GlideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex03_glide)
    }

    private fun setupBinding(){
        binding = ActivityEx03GlideBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupView(){
        binding.let{

        }
    }
}