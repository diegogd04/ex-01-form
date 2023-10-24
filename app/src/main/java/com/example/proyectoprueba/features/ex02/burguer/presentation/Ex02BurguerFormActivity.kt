package com.example.proyectoprueba.features.ex02.burguer.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.proyectoprueba.R
import com.example.proyectoprueba.databinding.ActivityEx02BurguerBinding
import com.example.proyectoprueba.features.ex02.burguer.data.BurguerDataRepository
import com.example.proyectoprueba.features.ex02.burguer.data.local.XmlLocalDataSource
import com.example.proyectoprueba.features.ex02.burguer.domain.Burguer
import com.example.proyectoprueba.features.ex02.burguer.domain.GetBurguerUseCase

class Ex02BurguerFormActivity : AppCompatActivity(){

    private lateinit var binding: ActivityEx02BurguerBinding

    private val viewModel: Ex02BurguerMainViewModel by lazy {
        Ex02BurguerMainViewModel(
            GetBurguerUseCase(
                BurguerDataRepository(
                    XmlLocalDataSource(
                        this,
                        GsonSerialization()
                    ), ApiMockRemoteDataSource()
                )
            )
        )
    }
    private lateinit var skeleton: Skeleton

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
        setContentView(binding.root)
    }

    private fun setupView(){
        skeleton = binding.layoutSkeleton
    }

    private fun setupObservers(){
        val observer = Observer<Ex02BurguerMainViewModel.UiState>{
            it.burguer?.apply{
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun bindData(burguer: Burguer){
        binding.apply {
            image.loadUrl(burguer.image)
            title.text(burguer.discount)
            description.text(burguer.description)
            rate.text(burguer.like)
            time.text(burguer.time)
        }
    }

}