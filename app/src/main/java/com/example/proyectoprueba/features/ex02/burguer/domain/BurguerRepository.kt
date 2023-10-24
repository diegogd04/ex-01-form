package com.example.proyectoprueba.features.ex02.burguer.domain

import com.example.proyectoprueba.app.ErrorApp
import com.example.proyectoprueba.features.ex01.domain.SaveUserUseCase
import com.iesam.kotlintrainning.Either

interface BurguerRepository {
    suspend fun obtain(): Either<ErrorApp, Burguer>
}