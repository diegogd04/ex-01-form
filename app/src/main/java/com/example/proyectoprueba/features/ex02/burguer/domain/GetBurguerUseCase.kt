package com.example.proyectoprueba.features.ex02.burguer.domain

import com.example.proyectoprueba.app.ErrorApp
import com.iesam.kotlintrainning.Either

class GetBurguerUseCase (private val repository: BurguerRepository) {
    suspend operator fun invoke(): Either<ErrorApp, Burguer>{
        return repository.obtain()
    }
}