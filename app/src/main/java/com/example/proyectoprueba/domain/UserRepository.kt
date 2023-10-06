package com.example.proyectoprueba.domain

import com.example.proyectoprueba.app.ErrorApp
import com.iesam.kotlintrainning.Either

interface UserRepository {

    fun save(input: SaveUserUseCase.Input): Either<ErrorApp, Boolean>
    fun obtain(): Either<ErrorApp, User>
}