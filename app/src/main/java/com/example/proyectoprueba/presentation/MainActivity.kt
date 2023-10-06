package com.example.proyectoprueba.presentation

import android.database.Observable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import com.example.proyectoprueba.R
import com.example.proyectoprueba.data.UserDataRepository
import com.example.proyectoprueba.data.local.XmlLocalDataSource
import com.example.proyectoprueba.domain.GetUserUseCase
import com.example.proyectoprueba.domain.SaveUserUseCase
import com.example.proyectoprueba.domain.User

class MainActivity : AppCompatActivity() {
        //Para usar esta creación se ha añadido: implementation "android.activity:activity-ktx:1.7.2"
    val viewModel: MainViewModel by lazy {
        MainViewModel(
            SaveUserUseCase(UserDataRepository(XmlLocalDataSource(this))),
            GetUserUseCase(UserDataRepository(XmlLocalDataSource(this)))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)
        setupView()
        setupObservers()
        viewModel.loadUser()
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

    private fun setupObservers(){
        val observer = Observer<MainViewModel.UiState>{
            //Código al notificar el observador
            it.user?.apply{
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun bindData(user: User){
        setNameInput(user.username)
        setSurnameInput(user.surname)
        setAgeInput(user.age)
    }

    private fun setNameInput(name: String){
        findViewById<EditText>(R.id.input_name).setText(name)
    }

    private fun setSurnameInput(surname: String){
        findViewById<EditText>(R.id.input_surname).setText(surname)
    }

    private fun setAgeInput(age: Int){
        findViewById<EditText>(R.id.input_age).setText("$age")
    }
}