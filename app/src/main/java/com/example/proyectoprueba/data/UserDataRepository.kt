package com.example.proyectoprueba.data

import com.example.proyectoprueba.app.ErrorApp
import com.example.proyectoprueba.data.local.XmlLocalDataSource
import com.example.proyectoprueba.domain.SaveUserUseCase
import com.example.proyectoprueba.domain.User
import com.example.proyectoprueba.domain.UserRepository
import com.iesam.kotlintrainning.Either

class UserDataRepository(private val localDataSource: XmlLocalDataSource) : UserRepository{
    override fun save(input: SaveUserUseCase.Input): Either<ErrorApp, Boolean> {
        return localDataSource.saveUser(input)
    }

    override fun obtain(): Either<ErrorApp, User>{
        return localDataSource.findUser()
    }
}