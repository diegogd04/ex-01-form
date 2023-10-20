package com.example.proyectoprueba.features.ex02.burguer.domain

import com.example.proyectoprueba.app.ErrorApp
import com.iesam.kotlintrainning.Either

class SaveBurguerUseCase(private val repository: BurguerRepository) {
    operator fun invoke(input: Input): Either<ErrorApp, Boolean>{
        return repository.save(input)
    }

    data class Input(val id: Int, val discount: String, val description: String, val like: String, val time: String)
}