package com.example.proyectoprueba.features.ex02.burguer.data

import com.example.proyectoprueba.app.ErrorApp
import com.example.proyectoprueba.features.ex02.burguer.data.local.XmlLocalDataSource
import com.example.proyectoprueba.features.ex02.burguer.data.remote.ApiMockRemoteDataSource
import com.example.proyectoprueba.features.ex02.burguer.domain.Burguer
import com.example.proyectoprueba.features.ex02.burguer.domain.BurguerRepository
import com.iesam.kotlintrainning.Either

class BurguerDataRepository(
    private val localDataSource: XmlLocalDataSource,
    private val remoteDataSource: ApiMockRemoteDataSource
) : BurguerRepository {

    override suspend fun obtain(): Either<ErrorApp, Burguer> {
        var burguer = localDataSource.findBurguer()
        burguer.mapLeft {errorApp ->
            return remoteDataSource.getBurguerMock().map { burguer ->
                localDataSource.saveBurguer(burguer)
                burguer
            }
        }
        return burguer
    }
}