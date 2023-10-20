package com.example.proyectoprueba.features.ex02.burguer.data.remote

import com.example.proyectoprueba.app.ErrorApp
import com.example.proyectoprueba.features.ex02.burguer.domain.Burguer
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.right

class ApiMockRemoteDataSource {
    fun getBurguerMock(): Either<ErrorApp, Burguer>{
        return Burguer(1, "-15%", "Burguer ", "98%", "20 - 30 min").right()
    }
}