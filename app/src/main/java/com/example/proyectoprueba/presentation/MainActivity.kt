package com.example.proyectoprueba.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.proyectoprueba.R
import com.example.proyectoprueba.data.UserDataRepository
import com.example.proyectoprueba.data.local.XmlLocalDataSource
import com.example.proyectoprueba.domain.SaveUserUseCase

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by lazy {
        MainViewModel(
            SaveUserUseCase(UserDataRepository(XmlLocalDataSource(this)))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)
        val actionButton = findViewById<Button>(R.id.action_save)
        setupView()
    }

    private fun setupView(){
        val actionButton = findViewById<Button>(R.id.action_save)
        actionButton.setOnClickListener {
            viewModel.saveUser(getNameInput(), getSurnameInput(), getAgeInput())
        }
    }

    private fun getNameInput(): String =
        findViewById<EditText>(R.id.input_name).text.toString()

    private fun getSurnameInput(): String =
        findViewById<EditText>(R.id.input_surname).text.toString()

    private fun getAgeInput(): Int =
        findViewById<EditText>(R.id.input_age).inputType.toInt()
}