package com.example.proyectoprueba.features.ex02.burguer.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.proyectoprueba.R
import com.example.proyectoprueba.databinding.ActivityEx02BurguerBinding
import com.example.proyectoprueba.features.ex01.presentation.Ex01MainViewModel
import com.example.proyectoprueba.features.ex02.burguer.data.BurguerDataRepository
import com.example.proyectoprueba.features.ex02.burguer.data.local.XmlLocalDataSource
import com.example.proyectoprueba.features.ex02.burguer.domain.Burguer
import com.example.proyectoprueba.features.ex02.burguer.domain.GetBurguerUseCase
import com.example.proyectoprueba.features.ex02.burguer.domain.SaveBurguerUseCase

class Ex02BurguerFormActivity : AppCompatActivity(){

    lateinit var binding: ActivityEx02BurguerBinding

    val viewModel: Ex02BurguerMainViewModel by lazy {
        Ex02BurguerMainViewModel(
            SaveBurguerUseCase(BurguerDataRepository(XmlLocalDataSource(this))),
            GetBurguerUseCase(BurguerDataRepository(XmlLocalDataSource(this)))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        bindView()
        setContentView(R.layout.activity_ex02_burguer)
        setupView()
        setupObservers()
        viewModel.loadBurguer()
    }

    private fun bindView(){
        binding = ActivityEx02BurguerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun setupView(){
        val actionButton = findViewById<Button>(R.id.action_save)
        viewModel.saveBurguer(0, getDiscountInput(), getDescriptionInput(), getLikeInput(), getTimeInput())
    }

    private fun getDiscountInput(): String {

    }

    private fun getDescriptionInput(): String {

    }

    private fun getLikeInput(): String {

    }

    private fun getTimeInput(): String {

    }

    private fun setupObservers(){
        val observer = Observer<Ex02BurguerMainViewModel.UiState>{
            //Código al notificar el observador
            it.burguer?.apply{
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun bindData(burguer: Burguer){
        setDiscountInput(burguer.discount)
        setDescriptionInput(burguer.description)
        setLikeInput(burguer.like)
        setTimeInput(burguer.time)
    }

    private fun setDiscountInput(discount: String){
        findViewById<TextView>(R.id.input_discount).setText(discount)
    }

    private fun setDescriptionInput(description: String){
        findViewById<TextView>(R.id.input_description).setText(description)
    }

    private fun setLikeInput(like: String){
        findViewById<TextView>(R.id.input_like).setText(like)
    }

    private fun setTimeInput(time: String){
        findViewById<TextView>(R.id.input_time).setText(time)
    }
}