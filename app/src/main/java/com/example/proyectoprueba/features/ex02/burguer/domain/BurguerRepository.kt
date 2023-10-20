package com.example.proyectoprueba.features.ex02.burguer.domain

import com.example.proyectoprueba.app.ErrorApp
import com.example.proyectoprueba.features.ex01.domain.SaveUserUseCase
import com.iesam.kotlintrainning.Either

interface BurguerRepository {
    fun save(input: SaveBurguerUseCase.Input): Either<ErrorApp, Boolean>
    fun obtain(): Either<ErrorApp, Burguer>
}