package com.example.proyectoprueba.features.ex01.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import com.example.proyectoprueba.R
import com.example.proyectoprueba.databinding.ActivityButtonBinding
import com.example.proyectoprueba.databinding.ActivityEx01FormBinding
import com.example.proyectoprueba.databinding.ActivityEx02Binding
import com.example.proyectoprueba.features.ex01.data.UserDataRepository
import com.example.proyectoprueba.features.ex01.data.local.XmlLocalDataSource
import com.example.proyectoprueba.features.ex01.domain.GetUserUseCase
import com.example.proyectoprueba.features.ex01.domain.SaveUserUseCase
import com.example.proyectoprueba.features.ex01.domain.User

class Ex01FormActivity : AppCompatActivity() {

    lateinit var binding: ActivityEx01FormBinding

        //Para usar esta creación se ha añadido: implementation "android.activity:activity-ktx:1.7.2"
    val viewModel: Ex01MainViewModel by lazy {
        Ex01MainViewModel(
            SaveUserUseCase(UserDataRepository(XmlLocalDataSource(this))),
            GetUserUseCase(UserDataRepository(XmlLocalDataSource(this)))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        setContentView(R.layout.activity_button)
        setupView()
        setupObservers()
        viewModel.loadUser()
    }

    private fun bindView(){
        binding = ActivityEx01FormBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
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
        val observer = Observer<Ex01MainViewModel.UiState>{
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