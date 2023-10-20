package com.example.proyectoprueba.features.ex02.burguer.data

import com.example.proyectoprueba.app.ErrorApp
import com.example.proyectoprueba.features.ex02.burguer.data.local.XmlLocalDataSource
import com.example.proyectoprueba.features.ex02.burguer.domain.Burguer
import com.example.proyectoprueba.features.ex02.burguer.domain.BurguerRepository
import com.example.proyectoprueba.features.ex02.burguer.domain.SaveBurguerUseCase
import com.iesam.kotlintrainning.Either

class BurguerDataRepository(private val localDataSource: XmlLocalDataSource) : BurguerRepository {
    override fun save(input: SaveBurguerUseCase.Input): Either<ErrorApp, Boolean> {
        return localDataSource.saveBurguer(input)
    }

    override fun obtain(): Either<ErrorApp, Burguer> {
        //Si está en local, devuelve lo de local.
        //Sino, voy a red, lo descargo y lo guardo en local.
        //Devuelves lo de red.
        return localDataSource.findBurguer()
    }
}