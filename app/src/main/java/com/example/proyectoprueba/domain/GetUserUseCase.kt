package com.example.proyectoprueba.domain

import com.example.proyectoprueba.app.ErrorApp
import com.iesam.kotlintrainning.Either

class GetUserUseCase(private val repository: UserRepository) {

    operator fun invoke(): Either<ErrorApp, User>{
        return repository.obtain()
    }
}