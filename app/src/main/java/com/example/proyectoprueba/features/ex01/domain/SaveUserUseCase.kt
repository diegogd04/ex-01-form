package com.example.proyectoprueba.features.ex01.domain

import com.example.proyectoprueba.app.ErrorApp
import com.iesam.kotlintrainning.Either

class SaveUserUseCase (private val repository: UserRepository){

    operator fun invoke(input: Input): Either<ErrorApp, Boolean> {
        return repository.save(input)
    }

    data class Input(val username: String, val surname: String, val age: Int)
}