package com.example.proyectoprueba.features.ex01.data

import com.example.proyectoprueba.app.ErrorApp
import com.example.proyectoprueba.features.ex01.data.local.XmlLocalDataSource
import com.example.proyectoprueba.features.ex01.domain.SaveUserUseCase
import com.example.proyectoprueba.features.ex01.domain.User
import com.example.proyectoprueba.features.ex01.domain.UserRepository
import com.iesam.kotlintrainning.Either

class UserDataRepository(private val localDataSource: XmlLocalDataSource) : UserRepository {
    override fun save(input: SaveUserUseCase.Input): Either<ErrorApp, Boolean> {
        return localDataSource.saveUser(input)
    }

    override fun obtain(): Either<ErrorApp, User>{
        return localDataSource.findUser()
    }
}